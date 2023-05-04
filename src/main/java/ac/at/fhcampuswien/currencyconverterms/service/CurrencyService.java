package ac.at.fhcampuswien.currencyconverterms.service;

import ac.at.fhcampuswien.currencyconverterms.exception.CurrencyServiceNotAvailableException;
import ac.at.fhcampuswien.currencyconverterms.model.CurrencyResponseDto;
import com.example.currencygrpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
@NoArgsConstructor
public class CurrencyService {

    private ManagedChannel channel;
    private CurrencyConverterServiceGrpc.CurrencyConverterServiceBlockingStub stub;

    public List<String> getCurrencyCodes() {
        initializeChannel();
        initializeStub(channel);
        CurrencyCodesResponse currencyCodes = stub.getCurrencyCodes(CurrencyCodesRequest.newBuilder()
                .build());
        channel.shutdown();
        return currencyCodes.getCurrencyCodesList();
    }

    //TODO
    public void getConvertedValue() {
        initializeChannel();
        initializeStub(channel);

        ConvertValueResponse convertedValue = stub.getConvertedValue(ConvertValueRequest.newBuilder()
                .setCurrentValue(1f)
                .setCurrentCurrencyCode("EUR")
                .setExpectedCurrencyCode("USD")
                .build());

        System.out.println(convertedValue.getRate());

        channel.shutdown();

    }

    private void initializeChannel() {
        String url = "grpc://ec2-3-72-65-240.eu-central-1.compute.amazonaws.com:8501";
        URI uri = URI.create(url);

        String hostname = uri.getHost();
        int port = uri.getPort();

        ManagedChannel channel = ManagedChannelBuilder.forAddress(hostname, port)
                .usePlaintext()
                .build();
        this.channel = channel;
    }

    private void initializeStub(ManagedChannel channel) {
        this.stub = CurrencyConverterServiceGrpc.newBlockingStub(channel);
    }
}
