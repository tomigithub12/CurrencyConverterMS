package ac.at.fhcampuswien.currencyconverterms.service;

import ac.at.fhcampuswien.currencyconverterms.exception.CurrencyServiceNotAvailableException;
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

    public List<String> getCurrencyCodes() throws CurrencyServiceNotAvailableException {
        initializeChannel();
        initializeStub(channel);

        try {
            CurrencyCodesResponse currencyCodes = stub.getCurrencyCodes(CurrencyCodesRequest.newBuilder()
                    .build());
            channel.shutdown();
            return currencyCodes.getCurrencyCodesList();
        } catch (Exception ex){
            throw new CurrencyServiceNotAvailableException("Currency Service not available!");
        }
    }


    public double getConvertedValue(float currentValue, String currentCurrency, String chosenCurrency) {
        initializeChannel();
        initializeStub(channel);

        ConvertValueResponse convertedValue = stub.getConvertedValue(ConvertValueRequest.newBuilder()
                .setCurrentValue(currentValue)
                .setCurrentCurrencyCode(currentCurrency)
                .setExpectedCurrencyCode(chosenCurrency)
                .build());

        channel.shutdown();

        return convertedValue.getRate();
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
