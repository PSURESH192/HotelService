import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return Hotel when ID input is given"
    request{
        method GET()
        url("/hotels/f387022e93774015aede2d4f5453a2aa") {

        }
    }
    response {
        body($(consumer(file("response.json"))))
        status 200
        headers {
            contentType(applicationJson())
        }
    }
}