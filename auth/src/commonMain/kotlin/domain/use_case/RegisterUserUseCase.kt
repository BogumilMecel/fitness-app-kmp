//package domain.use_case
//
//import com.gmail.bogumilmecel2.auth.ValidateAuthDataUseCase
//import com.gmail.bogumilmecel2.fitnessappv2.R
//import com.gmail.bogumilmecel2.fitnessappv2.common.domain.provider.ResourceProvider
//import com.gmail.bogumilmecel2.fitnessappv2.common.util.Resource
//import com.gmail.bogumilmecel2.fitnessappv2.feature_auth.domain.model.RegisterRequest
//import com.gmail.bogumilmecel2.fitnessappv2.feature_auth.domain.repository.AuthRepository
//import data.utils.Resource
//import domain.use_case.ValidateAuthDataUseCase
//import utils.Resource
//
//class RegisterUserUseCase(
//    private val authRepository: AuthRepository,
//    private val validateAuthDataUseCase: ValidateAuthDataUseCase
//) {
//
//    suspend operator fun invoke(
//        email: String,
//        password: String,
//        confirmPassword: String,
//        username: String
//    ): Resource<Unit> {
//        if (confirmPassword != password) return Resource.Error(resourceProvider.getString(R.string.please_make_sure_both_passwords_are_the_same))
//
//        val validationResult = validateAuthDataUseCase(
//            username = username,
//            email = email,
//            password = password
//        )
//
//        when (validationResult) {
//            ValidateAuthDataUseCase.Result.EmptyFields ->
//                return Resource.Error(resourceProvider.getString(R.string.empty_fields_error))
//
//            ValidateAuthDataUseCase.Result.EmailLengthInvalid ->
//                return Resource.Error(resourceProvider.getString(R.string.register_email_length_invalid))
//
//            ValidateAuthDataUseCase.Result.PasswordLengthInvalid ->
//                return Resource.Error(resourceProvider.getString(R.string.register_password_length_invalid))
//
//            ValidateAuthDataUseCase.Result.UsernameLengthInvalid ->
//                return Resource.Error(resourceProvider.getString(R.string.register_username_length_invalid))
//
//            ValidateAuthDataUseCase.Result.InvalidEmail ->
//                return Resource.Error(resourceProvider.getString(R.string.please_make_sure_you_have_entered_correct_email))
//
//            ValidateAuthDataUseCase.Result.Success -> return authRepository.registerUser(
//                registerRequest = RegisterRequest(
//                    username = username,
//                    email = email,
//                    password = password
//                )
//            )
//        }
//    }
//}