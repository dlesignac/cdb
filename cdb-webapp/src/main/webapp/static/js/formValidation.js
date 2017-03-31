$(document).ready(function() {
    $("#computerForm").on("submit", function(e) {
        var valid = checkComputerName() &&
            checkIntroduced() &&
            checkDiscontinued() &&
            checkCompanyId();

        if (!valid) {
            e.preventDefault();
        }
    });

    $("#computerName").on("input", function() {
        checkComputerName();
    });

    $("#introduced").on("input", function() {
        checkIntroduced();
    });

    $("#discontinued").on("input", function() {
        checkDiscontinued();
    });

    $("#companyId").on("input", function() {
        checkCompanyId();
    });
});

function checkComputerName() {
    var val = $("#computerName").val();
    var container = $("#computerNameContainer");

    if (/^.{1,255}$/.test(val)) {
        container.removeClass("has-error");
        container.addClass("has-success");
        return true;
    } else {
        container.removeClass("has-success");
        container.addClass("has-error");
        return false;
    }
}

function checkIntroduced() {
    var val = $("#introduced").val();
    var container = $("#introducedContainer");

    if (val == "" || /^\d{4}-(?:0[1-9]|1[0-2])-(?:0[1-9]|[1-2]\d|3[0-1])$/.test(val)) {
        container.removeClass("has-error");
        container.addClass("has-success");
        return true;
    } else {
        container.removeClass("has-success");
        container.addClass("has-error");
        return false;
    }
}

function checkDiscontinued() {
    var val = $("#discontinued").val();
    var container = $("#discontinuedContainer");

    if (val == "" || /^\d{4}-(?:0[1-9]|1[0-2])-(?:0[1-9]|[1-2]\d|3[0-1])$/.test(val)) {
        container.removeClass("has-error");
        container.addClass("has-success");
        return true;
    } else {
        container.removeClass("has-success");
        container.addClass("has-error");
        return false;
    }
}

function checkCompanyId() {
    var val = $("#companyId").val();
    var container = $("#companyIdContainer");

    container.removeClass("has-error");
    container.addClass("has-success");
    return true;
}