import React, {useEffect, useState} from "react";
import { Link } from "react-router-dom";

export default function Home() {

    const[message, setMessage] = useState("");

    useEffect(() => {
        const fetchMessage = async () => {
          try {
            const response = await fetch("http://localhost:8080/");
            const data = await response.text();
            console.log(data);
            setMessage(data);
          } catch (error) {
            console.log(error);
          }
        };
        fetchMessage();
    },[]);

  return (
    <div>
      <h1>Home Page</h1>
      <p>Welcome to the home page!</p>
      <Link to="/tierlist">Go to the Tierlist</Link>
        <h3>Message from API:</h3>    
        <p>{message}</p>

    </div>
  );
}