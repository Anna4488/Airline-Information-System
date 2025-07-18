// package io.github.fontysvenlo.ais.restapi;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.time.LocalDate;
// import java.util.ArrayList;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import io.github.fontysvenlo.ais.businesslogic.api.CustomerManager;
// import io.github.fontysvenlo.ais.datarecords.CustomerData;
// import io.javalin.http.Context;
// import io.github.fontysvenlo.ais.businesslogic.api.BusinessLogic;

// public class CustomerResourceTest {
//     private final Context context = mock(Context.class);

//     private BusinessLogic businessLogic;
//     private CustomerManager customerManager;
//     private CustomerResource customerResource;

//     @BeforeEach
//     public void setup() {
//         businessLogic = mock(BusinessLogic.class);
//         customerManager = mock(CustomerManager.class);
//         when(businessLogic.getCustomerManager()).thenReturn(customerManager);
//         customerResource = new CustomerResource(customerManager);
//     }

//     @Test
//     public void testGetAllCustomers200() {
//         // Arrange
//         when(customerManager.list()).thenReturn(new ArrayList<>());

//         // Act
//         customerResource.getAll(context);

//         // Assert
//         verify(context).status(200);
//     }

//     @Test
//     public void testPostCustomers201() {
//         // Arrange
//         CustomerData customerData = new CustomerData(0, "John", "Doe", LocalDate.of(2025, 1, 1), "john.doe@example.nl", "0612345678");
//         when(customerManager.add(customerData)).thenReturn(customerData);
//         when(context.bodyAsClass(CustomerData.class)).thenReturn(customerData);

//         // Act
//         customerResource.create(context);

//         // Assert
//         verify(context).status(201);
//         verify(context).json(customerData);
//     }

//     @Test
//     public void testPostCustomersNull400() {
//         // Arrange
//         when(context.body()).thenReturn(null);

//         // Act
//         customerResource.create(context);

//         // Assert
//         verify(context).status(400);
//     }
// }
