WITH JulyAggregated AS (
    SELECT
        FLAVOR,
        SUM(TOTAL_ORDER) AS TotalJulyOrder
    FROM
        JULY
    GROUP BY
        FLAVOR
)
SELECT
    f.FLAVOR
FROM
    FIRST_HALF f
JOIN
    JulyAggregated j ON f.FLAVOR = j.FLAVOR
ORDER BY
    (f.TOTAL_ORDER + j.TotalJulyOrder) DESC
LIMIT 3;
