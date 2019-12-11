package com.udacity.locationservice;

import com.udacity.locationservice.config.LocationServiceWebSecurityConfigureAdapter;
import com.udacity.locationservice.entity.Location;
import com.udacity.locationservice.service.LocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.net.HttpURLConnection;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;


@WebMvcTest
@RunWith(SpringRunner.class)
@Import(LocationServiceWebSecurityConfigureAdapter.class)
public class LocationServiceUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationService locationService;

    @MockBean
    private DataSource dataSource;

    @Test
    @WithMockUser()
    public void getAllLocation() throws Exception {
        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
        verify(locationService,times(1)).getAllLocation();
    }

    @Test
    @WithMockUser()
    public void getLocationByName() throws Exception{
        Mockito.when(locationService.getLocationByName("IAD")).thenReturn(Optional.of(Location.builder().name("IAD").id(1l).address("IAD").build()));
        mockMvc.perform(get("/locations/IAD"))
                .andExpect(status().isOk());
        verify(locationService,times(1)).getLocationByName("IAD");
    }
}
