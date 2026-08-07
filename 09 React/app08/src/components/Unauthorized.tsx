import { Card, CardBody, CardHeader, CardText, CardTitle } from "react-bootstrap";

function Unauthorized(){
    return (
        <Card>
            <CardHeader>Unauthorized</CardHeader>
            <CardBody>
                <CardTitle>Sorry! We can not allow your requst.</CardTitle>
                <CardText>
                    It seems like your user profile does not have enough credits 
                    for the requested operation.
                </CardText>
            </CardBody>
        </Card>        
    )
}

export default Unauthorized;