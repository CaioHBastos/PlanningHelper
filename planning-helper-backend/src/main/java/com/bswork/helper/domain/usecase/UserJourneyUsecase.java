package com.bswork.helper.domain.usecase;

import com.bswork.helper.domain.exception.BusyException;
import com.bswork.helper.domain.exception.NoContentUserException;
import com.bswork.helper.domain.exception.UserPresentException;
import com.bswork.helper.domain.gateway.UserGateway;
import com.bswork.helper.domain.model.UserDomain;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserJourneyUsecase {

    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;

    /**
     * Método responsável por buscar todos os usuários na camada de serviço
     * que implementa o gateway. Caso a lista de usuarios retorne vazio,
     * é lançado a exceção que não tem conteúdo.
     *
     * @return {@code List<UserDomain>}
     *      retorna a lista de usuários
     *
     */
    public List<UserDomain> getAllUsers() {
        List<UserDomain> usersDomain = userGateway.getAll();

        if (usersDomain.isEmpty()) {
            throw new NoContentUserException();
        }

        return usersDomain;
    }

    /**
     * Método responsável por buscar o usuário por ID na camada de serviço
     * que implementa o gateway. Caso os dados de usuário não existam,
     * é lançado a exceção que não tem os registros.
     *
     * @param id {@code Long}
     *
     * @return {@code UserDomain}
     *     retorna os dados do usuário
     *
     */
    public UserDomain getByIdUser(Long id) {
        return userGateway.getByIdUser(id);
    }

    /**
     * Método responsável por se comunicar com a camada de provider, através do gateway
     * para criar um usuário. Só é permitido criar um usuário, caso o email ainda não esteja cadastrado.
     * Caso esteja cadastrado, será lançado uma exceção que usuário já está presente.
     *
     * @param userDomain {@code UserDomain}
     *
     * @return {@code UserDomain}
     *      Retorna um usuario de dominio criado.
     */
    public UserDomain createUser(UserDomain userDomain) {
        Optional<UserDomain> userPresente = userGateway.findByEmail(userDomain.getEmail());

        if (userPresente.isPresent()) {
            throw new UserPresentException(String.format("O e-mail '%s' informado já está cadastrado", userDomain.getEmail()));
        }

        userDomain.setPassword(new BCryptPasswordEncoder().encode(userDomain.getPassword()));

        return userGateway.saveUser(userDomain);
    }

    /**
     * Método responsável por realizar a comunicação com a camada de dados para
     * deletar um usuário.
     *
     * @param id {@code Long}
     */
    public void deleteByIdUser(Long id) {
        getByIdUser(id);
        userGateway.deleteById(id);
    }

    /**
     * Método responsável por atualizar os dados do usuário, e guardar na base de dados
     * através da comunicação com o gateway.
     *
     * @param id {@code Long}
     * @param userNewPartial {@code UserDomain}
     *
     * @return {@code UserDomain}
     *      Usuário atualizado.
     */
    public UserDomain updateUserPartial(Long id, UserDomain userNewPartial) {
        UserDomain userDadosActual = getByIdUser(id);

        if (Objects.nonNull(userNewPartial.getName())) {
            userDadosActual.setName(userNewPartial.getName());
        }

        if (Objects.nonNull(userNewPartial.getEmail())) {
            userDadosActual.setEmail(userNewPartial.getEmail());
        }

        return userGateway.updatePartial(userDadosActual);
    }

    /**
     * Método responsável por atualizar a senha na base de dados.
     *
     * @param id {@code Long}
     * @param userNewPassword {@code UserDomain}
     * @return {@code UserDomain}
     */
    public UserDomain updateUserUpdatePassword(Long id, UserDomain userNewPassword) {
        UserDomain userDadosActual = getByIdUser(id);

        System.out.println(passwordEncoder.encode(userNewPassword.getPassword()));
        System.out.println(userDadosActual.getPassword());

        verifyIfDifferencePassword(userDadosActual.getPassword(), userNewPassword.getPassword());
        verifyIfOldPasswordEqualsNewPassword(userNewPassword.getPassword(), userNewPassword.getNewPassword());
        userDadosActual.setPassword(passwordEncoder.encode(userNewPassword.getNewPassword()));
        return userGateway.updatePartial(userDadosActual);
    }

    /**
     * Método responsável por verificar se a senha que está na base de dados, é diferente da senha corrente
     * que foi informado na entrada.
     *
     * @param userDadosActual {@code String}
     *      - senha atual da base de dados
     * @param userNewPassword {@code String}
     *      - senha nova
     */
    private void verifyIfDifferencePassword(String userDadosActual, String userNewPassword) {
        if (passwordEncoder.matches(userDadosActual, userNewPassword)) {
            throw new BusyException(String.format("A senha informada é diferente da senha antiga, informe a senha correta."));
        }
    }

    /**
     * Método responsável por verificar se a senha atual é igual a senha nova informado na entrada.
     *
     * @param password {@code String}
     *      - senha atual
     * @param newPassword {@code String}
     *      - senha nova
     */
    private void verifyIfOldPasswordEqualsNewPassword(String password, String newPassword) {
        if (password.equals(newPassword)) {
            throw new BusyException(String.format("Não é possível atualizar a senha, porque a nova senha informada é igual a senha antiga."));
        }
    }

    public UserDomain forgotPassword(String question, String answer) {
        return userGateway.findByQuestionAndAnswer(question, answer);
    }
}
