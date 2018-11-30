package Callback;

public interface UnapprovedBarbers {

    void Success(String[] barbers);

    void Fail(String error);
}
