document.addEventListener("DOMContentLoaded", function () {
  const params = new URLSearchParams(window.location.search);
  const studentId = params.get("studentId");
  const apiUrl = `http://localhost:8080/student/moyenne/${studentId}`;
  const tableBody = document.getElementById("notes");

  if (!studentId) {
    return "Erreur: Pas d'ID fournis";
  } else {
    fetch(`http://localhost:8080/student/${studentId}`)
      .then((response) => {
        if (!response.ok) throw new Error("Erreur de la requête");
        return response.json();
      })
      .then((data) => {
        data.forEach((note) => {
          const row = document.createElement("tr");

          const tdId = document.createElement("td");
          tdId.textContent = note.id;
          row.appendChild(tdId);

          const tdMatiere = document.createElement("td");
          tdMatiere.textContent = note.matiere;
          row.appendChild(tdMatiere);

          const tdValeur = document.createElement("td");
          tdValeur.textContent = note.valeur;
          row.appendChild(tdValeur);

          const tdLien = document.createElement("td");
          const lien = document.createElement("a");
          lien.textContent = "Supprimer";
          lien.className = "supprimer-link";
          lien.addEventListener("click", function () {
            fetch(`http://localhost:8080/notes/${note.id}`, {
              method: "DELETE",
            }).then((response) => {
              if (response.ok) {
                alert("Note supprimée avec succès!");
                row.remove();
              } else {
                alert("Erreur lors de la suppression");
              }
            });
          });
          tdLien.appendChild(lien);
          row.appendChild(tdLien);

          const tdModifier = document.createElement("td");
          const modifier = document.createElement("a");
          modifier.textContent = "Modifier";
          modifier.className ="modifier-link";
          modifier.addEventListener("click", function (e) {
            e.preventDefault();
            const nouvelleMatiere = prompt("Nouvelle matiere :", note.matiere);
            const nouvelleValeur = prompt("Nouvelle note :", note.valeur);

            if (nouvelleMatiere && nouvelleValeur) {
              fetch(`http://localhost:8080/notes/${note.id}`,
                {
                  method: "PUT",
                  headers: {
                    "Content-Type": "application/json",
                  },
                  body: JSON.stringify({
                    matiere: nouvelleMatiere,
                    valeur: nouvelleValeur
                  }),
                }).then((response) => {
                if (response.ok) {
                  window.location.reload();
                } else {
                  alert("Erreur lors de la modification");
                }
              });
            }
          });

          tdModifier.appendChild(modifier);
          row.appendChild(tdModifier);

          tableBody.appendChild(row);
        });
        container.appendChild(tableBody);
      })
      .catch((error) => {
        console.error(error);
      });
  }

  fetch(apiUrl)
  .then(response => {
    if (!response.ok) throw new Error("Erreur lors de la récupération de la moyenne");
    return response.text();
  })
  .then(moyenne => {
    const divMoyenne = document.getElementById("moyenne");
    if(divMoyenne){
      divMoyenne.textContent = moyenne;
    }
  })
  .catch(error => {
    console.error(error);
  });

  const form = document.getElementById("noteForm");
  form.addEventListener("submit", function (event) {
    event.preventDefault();
    const data = {
      matiere: form.matiere.value,
      valeur: form.valeur.value,
    };

    fetch(`http://localhost:8080/notes/${studentId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((response) => {
        if (!response.ok) throw new Error("Erreur lors de l'ajout note.");
        return response.json();
      })
      .then(() => {
        form.reset();
        window.location.reload();
      })
      .catch((error) => {
        console.error(error);
      });
  });
});
