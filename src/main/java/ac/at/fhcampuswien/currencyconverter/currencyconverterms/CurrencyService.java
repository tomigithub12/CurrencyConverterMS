package ac.at.fhcampuswien.currencyconverter.currencyconverterms;

import com.example.currencygrpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class CurrencyService {

    private ManagedChannel channel;
    private CurrencyConverterServiceGrpc.CurrencyConverterServiceBlockingStub stub;

    public void getCurrencyCodes(){
        initializeChannel();
        initializeStub(channel);
        CurrencyCodesResponse currencyCodes = stub.getCurrencyCodes(CurrencyCodesRequest.newBuilder()
                .build());

        System.out.println(currencyCodes.getCurrencyCodesList());

        channel.shutdown();
    }

    public void getConvertedValue(){
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

    private void initializeChannel(){
        String url = "grpc://ec2-3-72-65-240.eu-central-1.compute.amazonaws.com:8501";
        URI uri = URI.create(url);

        String hostname = uri.getHost();
        int port = uri.getPort();

        ManagedChannel channel = ManagedChannelBuilder.forAddress(hostname, port)
                .usePlaintext()
                .build();
        this.channel = channel;
    }

    private void initializeStub(ManagedChannel channel){
        this.stub = CurrencyConverterServiceGrpc.newBlockingStub(channel);
    }
}
