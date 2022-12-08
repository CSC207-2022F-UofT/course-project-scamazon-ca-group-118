package useCase.Register;

import org.junit.jupiter.api.Test;

public class RegisterPresenterUnitTests {

    @Test
    void TestRegisterPresenterGetter(){
        RegisterPresenter registerPresenter = new RegisterPresenter("Hello");
        assert (registerPresenter.getMessage().equals("Hello"));
    }

    @Test
    void TestRegisterPresenterSetter(){
        RegisterPresenter registerPresenter = new RegisterPresenter("");
        registerPresenter.setMessage("Hello");
        assert (registerPresenter.getMessage().equals("Hello"));
    }
}
