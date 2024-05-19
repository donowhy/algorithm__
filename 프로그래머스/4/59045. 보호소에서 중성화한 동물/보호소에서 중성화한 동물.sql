SELECT ins.animal_id, ins.animal_type, ins.name
FROM (
    SELECT animal_id, animal_type, name
    FROM animal_ins 
    WHERE sex_upon_intake NOT IN ('Spayed Female', 'Neutered Male')
) ins 
JOIN animal_outs outs 
ON ins.animal_id = outs.animal_id 
WHERE outs.sex_upon_outcome IN ('Spayed Female', 'Neutered Male');
