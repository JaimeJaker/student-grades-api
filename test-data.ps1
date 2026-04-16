# Script para llenar la base de datos con datos de prueba

$baseUrl = "http://localhost:8080"

# Arrays de nombres para variar
$nombres = @("Juan", "Maria", "Carlos", "Ana", "Pedro", "Sofia", "Luis", "Elena", "Diego", "Laura")
$apellidos = @("Garcia", "Martinez", "Rodriguez", "Lopez", "Gonzalez", "Fernandez", "Torres", "Ramirez", "Cruz", "Santos")

$materias = @(
    @{nombre="Mathematics"; codigo="MAT101"; creditos=4},
    @{nombre="Physics"; codigo="PHY101"; creditos=3},
    @{nombre="Chemistry"; codigo="CHE101"; creditos=3},
    @{nombre="Biology"; codigo="BIO101"; creditos=4},
    @{nombre="History"; codigo="HIS101"; creditos=2}
)

$alumnoIds = @()
$materiaIds = @()

# Crear 5 materias
Write-Host "Creando materias..."
foreach ($materia in $materias) {
    $body = $materia | ConvertTo-Json
    $response = Invoke-WebRequest -Uri "$baseUrl/materias" -Method POST -Headers @{"Content-Type"="application/json"} -Body $body -UseBasicParsing
    $materiaObj = $response.Content | ConvertFrom-Json
    $materiaIds += $materiaObj.id
    Write-Host "  Materia creada: $($materiaObj.nombre) (ID: $($materiaObj.id))"
}

# Crear 10 alumnos
Write-Host "`nCreando alumnos..."
for ($i = 0; $i -lt 10; $i++) {
    $nombre = $nombres[$i]
    $apellido = $apellidos[$i]
    $email = "$($nombre.ToLower()).$($apellido.ToLower())@example.com"
    $fecha = (Get-Date).AddYears(-20).AddMonths(($i % 12)).AddDays(($i * 3)).ToString("yyyy-MM-dd")
    
    $alumno = @{
        nombre = $nombre
        apellido = $apellido
        email = $email
        fechaNacimiento = $fecha
    }
    
    $body = $alumno | ConvertTo-Json
    $response = Invoke-WebRequest -Uri "$baseUrl/alumnos" -Method POST -Headers @{"Content-Type"="application/json"} -Body $body -UseBasicParsing
    $alumnoObj = $response.Content | ConvertFrom-Json
    $alumnoIds += $alumnoObj.id
    Write-Host "  Alumno creado: $nombre $apellido (ID: $($alumnoObj.id))"
}

# Crear notas (cada alumno con 5 materias)
Write-Host "`nCreando notas..."
$notasCreadas = 0
foreach ($alumnoId in $alumnoIds) {
    foreach ($materiaId in $materiaIds) {
        # Generar nota aleatoria entre 0 y 5
        $valor = [Math]::Round((Get-Random -Minimum 0 -Maximum 500) / 100, 1)
        
        $nota = @{
            alumnoId = $alumnoId
            materiaId = $materiaId
            valor = $valor
        }
        
        $body = $nota | ConvertTo-Json
        try {
            $response = Invoke-WebRequest -Uri "$baseUrl/notas" -Method POST -Headers @{"Content-Type"="application/json"} -Body $body -UseBasicParsing
            $notasCreadas++
            Write-Host "  Nota creada: Alumno $alumnoId, Materia $materiaId, Valor: $valor"
        }
        catch {
            Write-Host "  Error creando nota: $_" -ForegroundColor Red
        }
    }
}

Write-Host "`n=== RESUMEN ===" -ForegroundColor Green
Write-Host "Materias creadas: $($materiaIds.Count)"
Write-Host "Alumnos creados: $($alumnoIds.Count)"
Write-Host "Notas creadas: $notasCreadas"
Write-Host "Total esperado: $($alumnoIds.Count * $materiaIds.Count)"
