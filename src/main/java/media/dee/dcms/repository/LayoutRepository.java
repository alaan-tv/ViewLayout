package media.dee.dcms.repository;

import java.util.Map;

public interface LayoutRepository {

    Map<String, Object> findOne(String id);
}
