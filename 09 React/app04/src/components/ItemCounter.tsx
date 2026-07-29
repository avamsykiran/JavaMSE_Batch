import { useEffect, useState } from "react";
import { Button, Card, CardBody, CardFooter, CardHeader } from "react-bootstrap";

function ItemCount() {

    const [items, setItems] = useState<number>(0);
    const [packets, setPackets] = useState<number>(0);

    useEffect(() => {
        setItems(1);
    }, []);

    useEffect(() => {
        if (items < 0 && packets === 0) {
            setItems(0);
        } else if (items < 0 && packets > 0) {
            setItems(9);
            setPackets(cv => cv - 1);
        } else if (items === 10) {
            setItems(0);
            setPackets(cv => cv + 1);
        }
    }, [items]);

    return (
        <Card>
            <CardHeader>
                <h3>Life cycle methods demo</h3>
            </CardHeader>
            <CardBody>
                <p>
                    Quantity: <strong>{items}</strong> Items and <strong>{packets} packs.</strong>
                </p>
            </CardBody>
            <CardFooter className="text-end">
                <Button type="button" onClick={_e => setItems(cv => cv - 1)}> Remove </Button>
                <Button type="button" onClick={_e => setItems(cv => cv + 1)}> Add </Button>
            </CardFooter>
        </Card>
    )
}


export default ItemCount;