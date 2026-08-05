import { Card, CardBody, CardHeader, CardText, CardTitle } from "react-bootstrap";
import { Link } from "react-router";

function PathNotFound(){
    return (
        <Card bg="danger" text="light">
            <CardHeader>Path Not Found</CardHeader>
            <CardBody>
                <CardTitle>Sorry! The path you are looking for is not found!</CardTitle>
                <CardText>
                    The following may be the reasons for this screen to appear.
                    <ul>
                        <li>You may have entered a URL manually and that doesn't exist on the app</li>
                        <li>You may have clicked a link that no longer works</li>                        
                    </ul>
                    <Link to="/" className="btn btn-primary">Go Home</Link>
                </CardText>
            </CardBody>
        </Card>        
    )
}

export default PathNotFound;