import { useEffect, useState } from "react";

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
        <section>
            <h3>Life cycle methods demo</h3>
            <p>
                Quantity: <strong>{items}</strong> Items and <strong>{packets} packs.</strong>
            </p>
            <button type="button" onClick={_e => setItems( cv => cv -1)}> Remove </button>
            <button type="button" onClick={_e => setItems( cv => cv +1)}> Add </button>
        </section>
    )
}


export default ItemCount;