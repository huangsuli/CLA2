package servlet;

import javax.inject.*;

import play.db.*;
import scala.Int;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Singleton
class DatabaseAccess {

    private Database db;
    private DatabaseExecutionContext executionContext;

    @Inject
    public JavaApplicationDatabase(Database db, DatabaseExecutionContext context) {
        this.db = db;
        this.executionContext = executionContext;
    }

    public CompletionStage<Integer> updateSomething() {
        return CompletableFuture.supplyAsync(
                () -> {
                    return db.withConnection(
                            con -> {
                                String query = "UPDATE myTable SET Counter = Counter + 1";
                                Statement stmt = con.createStatement())
                                Integer result = stmt.executeUpdate(query);
                                return result;
                            });
                },
                executionContext);
    }
}