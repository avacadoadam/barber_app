package UI;

import javafx.scene.input.MouseEvent;

public class Start extends CustomController {


    public void AdminForm(MouseEvent mouseEvent) {
        ChangeScene("adminform.fxml", "Admin Form");
    }

    public void CustomerForm(MouseEvent mouseEvent) {

        ChangeScene("customerform.fxml", "Customer Form");
    }

    public void BarberForm(MouseEvent mouseEvent) {
        ChangeScene("barberform.fxml", "Barber Form");
    }
}
