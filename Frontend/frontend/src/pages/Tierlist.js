import React from "react";
import { Link } from "react-router-dom";

export default function Tierlist() {
  return (
    <div>
      <h1>TierList Page</h1>
      <p>Welcome to the TierList page!</p>
      <Link to="/">Go to Home Page</Link>
    </div>
  );
}