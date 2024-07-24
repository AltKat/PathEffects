package io.github.altkat.pathEffects.database

import org.bukkit.entity.Player
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.UUID

class database(private val dbUrl: String) {
    private var connection: Connection? = null

    fun connect() {
        if (connection == null || connection?.isClosed == true) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:$dbUrl")
                println("Connected to the database.")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createTable() {
        val createTableSql = """
            CREATE TABLE IF NOT EXISTS players (
                uuid TEXT PRIMARY KEY,
                nick TEXT NOT NULL,
                time INTEGER NOT NULL,
                status INTEGER NOT NULL,
                type TEXT NOT NULL
            )
        """
        var statement: java.sql.Statement? = null
        try {
            statement = connection?.createStatement()
            statement?.executeUpdate(createTableSql)
            println("Table created or already exists.")
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            statement?.close()
        }
    }

    fun insertPlayer(uuid: String, nick: String, time: Int, status: Int, type: String) {
        val insertSql = "INSERT INTO players (uuid, nick, time, status, type) VALUES (?, ?, ?, ?,?)"
        var preparedStatement: PreparedStatement? = null
        try {
            preparedStatement = connection?.prepareStatement(insertSql)
            preparedStatement?.setString(1, uuid)
            preparedStatement?.setString(2, nick)
            preparedStatement?.setInt(3, time)
            preparedStatement?.setInt(4, status)
            preparedStatement?.setString(5, type)
            preparedStatement?.executeUpdate()
            println("Inserted player with UUID: $uuid")
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            preparedStatement?.close()
        }
    }

    fun checkifPlayerExists(player: Player): Boolean{
        var preparedStatement: PreparedStatement? = null
        var resultSet: ResultSet? = null
        try {
            preparedStatement = connection?.prepareStatement("SELECT 1 FROM players WHERE uuid = ?")
            preparedStatement?.setString(1, player.uniqueId.toString())
            resultSet = preparedStatement?.executeQuery()
            return resultSet?.next() == true
        }catch (e: Exception){
            e.printStackTrace()
            return false
        }finally {
            resultSet?.close()
            preparedStatement?.close()
        }
    }

    fun getPlayerStatus(player: Player): Int {
        var preparedStatement: PreparedStatement? = null
        var resultSet: ResultSet? = null
        try {
            preparedStatement = connection?.prepareStatement("SELECT status FROM players WHERE uuid = ?")
            preparedStatement?.setString(1, player.uniqueId.toString())
            resultSet = preparedStatement?.executeQuery()
            if (resultSet?.next() == true) {
                return resultSet.getInt("status")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            resultSet?.close()
            preparedStatement?.close()
        }
        return 0
    }

    fun setPlayerStatus(player: Player) {
        var preparedStatementSelect: PreparedStatement? = null
        var preparedStatementUpdate: PreparedStatement? = null
        var resultSet: ResultSet? = null
        try {
            preparedStatementSelect = connection?.prepareStatement("SELECT status FROM players WHERE uuid = ?")
            preparedStatementSelect?.setString(1, player.uniqueId.toString())
            resultSet = preparedStatementSelect?.executeQuery()
            if (resultSet?.next() == true) {
                val newStatus = if (resultSet.getInt("status") == 0) 1 else 0
                preparedStatementUpdate = connection?.prepareStatement("UPDATE players SET status = ? WHERE uuid = ?")
                preparedStatementUpdate?.setInt(1, newStatus)
                preparedStatementUpdate?.setString(2, player.uniqueId.toString())
                preparedStatementUpdate?.executeUpdate()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            resultSet?.close()
            preparedStatementSelect?.close()
            preparedStatementUpdate?.close()
        }
    }

    fun getPlayerPathType(player: Player): String{
        var preparedStatement: PreparedStatement? = null
        var resultSet: ResultSet? = null

        try {
            preparedStatement = connection?.prepareStatement("SELECT type FROM players WHERE uuid = ?")
            preparedStatement?.setString(1, player.uniqueId.toString())
            resultSet = preparedStatement?.executeQuery()
            if(resultSet?.next() == true){
                return resultSet.getString("type")
            }
        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            resultSet?.close()
            preparedStatement?.close()
        }
        return ""
    }

    fun setPlayerPathType(player: Player, newType : String) {
        var preparedStatementSelect: PreparedStatement? = null
        var preparedStatementUpdate: PreparedStatement? = null
        var resultSet: ResultSet? = null
        try {
            preparedStatementSelect = connection?.prepareStatement("SELECT type FROM players WHERE uuid = ?")
            preparedStatementSelect?.setString(1, player.uniqueId.toString())
            resultSet = preparedStatementSelect?.executeQuery()
            if (resultSet?.next() == true) {
                preparedStatementUpdate = connection?.prepareStatement("UPDATE players SET type = ? WHERE uuid = ?")
                preparedStatementUpdate?.setString(1, newType)
                preparedStatementUpdate?.setString(2, player.uniqueId.toString())
                preparedStatementUpdate?.executeUpdate()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            resultSet?.close()
            preparedStatementSelect?.close()
            preparedStatementUpdate?.close()
        }
    }

    fun close() {
        try {
            connection?.close()
            println("Database connection closed.")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

data class DatabasePlayer(val uuid: String, val nick: String, val time: Int, val status: Int)