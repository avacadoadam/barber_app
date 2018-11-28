package Callback;

import Dataset.ListBarber;

public interface ListBarbersController {

void Success(ListBarber[] barbers);
void Fail(String error);


}
