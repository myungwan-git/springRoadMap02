package hello.core.lifecycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

  private String url;

  public NetworkClient() {
    System.out.println("생성자 호출 , url " + url);

  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void connet() {
    System.out.println("connect : " + url);

  }

  public void call(String message) {
    System.out.println("call : " + url + " message : " + message);

  }

  public void disconnect() {
    System.out.println(" close ! " + url);
  }

  //의존주입이 끝나면 자동으로 호출되는 메서드 ( 상속 받음 )
  // 의존주입이 되기전에 DB커넥트가 되면 안되니 .
  @PostConstruct
  public void init(){
    connet();
    call("초기화 메시지 ( 연결 후 )");
  }

  @PreDestroy
  // 종료되기 직전에 호출되는 매서드.
  public void close() {
    disconnect();
  }
}
