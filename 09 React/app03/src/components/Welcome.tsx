import { useState } from "react";

const titles = [
  { title: "Mr.", label: "GentelMan" },
  { title: "Mrs.", label: "Lady" },
  { title: "Dr.", label: "Doctor" },
  { title: "Prof.", label: "Professor" },
  { title: "Master.", label: "Boy Child" },
  { title: "Baby.", label: "Girl Child" },
  { title: "", label: "--SELECT---" }
];

function Welcome() {

  const [title, setTitle] = useState<string>("");
  const [userName, setUserName] = useState<string>("SomeBody");

  return (
    <section className="card card-primary m-2 p-4">
      <h3> Welcome {title} {userName}</h3>
      <form>
        <label>
          title:
          <select
            value={title}
            onChange={e => setTitle(e.target.value)}>
            {
              titles.map(t => (
                <option key={t.title} value={t.title}>{t.label}</option>
              ))
            }
          </select>
        </label>
        <label>
          User Name:
          <input type="text" value={userName}
            onChange={e => setUserName(e.target.value)} />
        </label>
      </form>
    </section>
  );
}

export default Welcome;
