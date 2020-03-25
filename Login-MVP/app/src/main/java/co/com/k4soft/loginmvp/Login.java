package co.com.k4soft.loginmvp;

import co.com.k4soft.loginmvp.dto.LoginDTO;

public interface Login {

    interface View{

        void aceptarIngreso();

        LoginDTO getLogin();

        void rechazarCredenciales();

        void solicitarCredenciales();

        void solicitarPassword();

        void solicitarUsuario();
    }

    interface Presenter{

        void aceptarIngreso();

        void rechazarCredenciales();

        void solicitarCredenciales();

        void solicitarPassword();

        void solicitarUsuario();

        void validarInformacion();
    }

    interface Model{

        void setLogin(LoginDTO login);

        void validarInformacion();
    }


}
