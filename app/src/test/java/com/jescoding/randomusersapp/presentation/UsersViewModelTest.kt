import com.google.common.truth.Truth.assertThat
import com.jescoding.randomusersapp.data.User
import com.jescoding.randomusersapp.dispatchers.DispatcherProvider
import com.jescoding.randomusersapp.dispatchers.TestDispatchers
import com.jescoding.randomusersapp.domain.RandomUsersRepository
import com.jescoding.randomusersapp.presentation.UsersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class UsersViewModelTest {

    @Mock
    private lateinit var repository: RandomUsersRepository

    private lateinit var viewModel: UsersViewModel
    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockitoAnnotations.openMocks(this)
        dispatcherProvider = TestDispatchers()
        viewModel = UsersViewModel(dispatcherProvider, repository)
    }

    @Test
    fun hideBottomSheet_updatesState() = runTest {
        viewModel.hideBottomSheet()
        assertThat(viewModel.uiState.value.showBottomSheet).isFalse()
    }

    @Test
    fun showBottomSheet_updatesState() = runTest {
        viewModel.showBottomSheet()
        assertThat(viewModel.uiState.value.showBottomSheet).isTrue()
    }

    @Test
    fun updateInput_updatesState() = runTest {
        val input = "123"
        viewModel.updateInput(input)
        assertThat(viewModel.uiState.value.input).isEqualTo(input)
        assertThat(viewModel.uiState.value.inputError).isFalse()
        assertThat(viewModel.uiState.value.error).isEmpty()
    }

    @Test
    fun validateInput_withValidInput_generatesUsers(): Unit = runTest {
        val input = "100"

        `when`(repository.getRandomUsersList(input)).thenReturn(flow { emit(emptyList()) })

        viewModel.updateInput(input)
        viewModel.validateInput()

        assertThat(viewModel.uiState.value.shouldGenerateUsers).isTrue()
    }

    @Test
    fun validateInput_withInvalidInput_Zero_setsInputError() = runTest {
        val input = "0"
        viewModel.updateInput(input)
        viewModel.validateInput()
        assertThat(viewModel.uiState.value.inputError).isTrue()
    }

    @Test
    fun validateInput_withInvalidInput_HigherThanFiveThousand_setsInputError() = runTest {
        val input = "5001"
        viewModel.updateInput(input)
        viewModel.validateInput()
        assertThat(viewModel.uiState.value.inputError).isTrue()
    }

    @Test
    fun validateInput_withInvalidInput_Letters_setsInputError() = runTest {
        val input = "asvas"
        viewModel.updateInput(input)
        viewModel.validateInput()
        assertThat(viewModel.uiState.value.inputError).isTrue()
    }


    @Test
    fun selectUser_updatesCurrentUser() = runTest {
        val user = User(
            "John Doe",
            "john@example.com",
            "123 Street",
            "url",
            "username",
            "male",
            "12345",
            "2000-01-01",
            "1234567890",
            "0987654321"
        )
        viewModel.selectUser(user)
        assertThat(viewModel.uiState.value.currentUser).isEqualTo(user)
    }

    @Test
    fun generateUsers_withEmptyResponse_setsError() = runTest {
        `when`(repository.getRandomUsersList(anyString())).thenReturn(flow { emit(emptyList<User>()) })
        viewModel.updateInput("100")
        viewModel.generateUsers()
        assertThat(viewModel.uiState.value.error).isEqualTo("An error occurred while fetching users")
        assertThat(viewModel.uiState.value.isLoading).isFalse()
    }

    @Test
    fun generateUsers_withValidResponse_updatesUsers() = runTest {
        val users = listOf(
            User(
                "John Doe",
                "john@example.com",
                "123 Street",
                "url",
                "username",
                "male",
                "12345",
                "2000-01-01",
                "1234567890",
                "0987654321"
            )
        )
        `when`(repository.getRandomUsersList(anyString())).thenReturn(flow { emit(users) })
        viewModel.updateInput("100")
        viewModel.generateUsers()
        assertThat(viewModel.uiState.value.users).isEqualTo(users)
        assertThat(viewModel.uiState.value.isLoading).isFalse()
    }


}