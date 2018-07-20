package libraryloansys.dao;

public interface BaseDAO {
    public void setup() throws Exception;
    public void connect() throws Exception;
    public void close() throws Exception;
}
