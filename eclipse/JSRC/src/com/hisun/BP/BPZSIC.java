package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSIC {
    String JIBS_tmp_str[] = new String[10];
    String PGM_SCSSCKDT = "SCSSCKDT";
    int WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCOFBAS BPCOFBAS = new BPCOFBAS();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCCCKWD BPCCCKWD = new BPCCCKWD();
    BPCQCHEK BPCQCHEK = new BPCQCHEK();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPQPDM BPCPQPDM = new BPCPQPDM();
    SCCGWA SCCGWA;
    BPCSIC BPCSIC;
    public void MP(SCCGWA SCCGWA, BPCSIC BPCSIC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSIC = BPCSIC;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSIC return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSIC.FUNC.equalsIgnoreCase("01")) {
            B01_CHK_PROD_CD();
        } else if (BPCSIC.FUNC.equalsIgnoreCase("02")) {
            B02_CHK_FEE_CD();
        } else if (BPCSIC.FUNC.equalsIgnoreCase("03")) {
            B03_CHK_CCY();
        } else if (BPCSIC.FUNC.equalsIgnoreCase("04")) {
            B04_CHK_CI_NO();
        } else if (BPCSIC.FUNC.equalsIgnoreCase("05")) {
            B05_CHK_AC();
        } else if (BPCSIC.FUNC.equalsIgnoreCase("06")) {
            B06_CHK_CHQ_NO();
        } else if (BPCSIC.FUNC.equalsIgnoreCase("07")) {
            B07_CHK_HOLI_CHK_CODE();
        } else if (BPCSIC.FUNC.equalsIgnoreCase("08")) {
            B08_CHK_HOLI();
        } else if (BPCSIC.FUNC.equalsIgnoreCase("09")) {
            B09_CHK_DATE();
        } else if (BPCSIC.FUNC.equalsIgnoreCase("10")) {
            B10_CHK_BR();
        } else if (BPCSIC.FUNC.equalsIgnoreCase("11")) {
            B11_CHK_CNTR_TYPE();
        } else if (BPCSIC.FUNC.equalsIgnoreCase("12")) {
            B12_CHK_OIC_NO();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCSIC.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B01_CHK_PROD_CD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIC.PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = BPCSIC.PROD_CD;
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            CEP.TRC(SCCGWA, "ERROR - PROD-CD");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B02_CHK_FEE_CD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIC.FEE_CD);
        IBS.init(SCCGWA, BPCOFBAS);
        BPCOFBAS.FUNC = 'I';
        BPCOFBAS.KEY.FEE_CODE = BPCSIC.FEE_CD;
        IBS.CALLCPN(SCCGWA, "BP-F-U-MAINTAIN-FBAS", BPCOFBAS);
    }
    public void B03_CHK_CCY() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIC.CCY);
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = BPCSIC.CCY;
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        CEP.TRC(SCCGWA, BPCQCCY.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            CEP.TRC(SCCGWA, "ERROR - CCY");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B04_CHK_CI_NO() throws IOException,SQLException,Exception {
    }
    public void B05_CHK_AC() throws IOException,SQLException,Exception {
    }
    public void B06_CHK_CHQ_NO() throws IOException,SQLException,Exception {
    }
    public void B07_CHK_HOLI_CHK_CODE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIC.HOLI_CHK_CODE);
        IBS.init(SCCGWA, BPCQCHEK);
        BPCQCHEK.CHECK_CODE = BPCSIC.HOLI_CHK_CODE;
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-CHEK-CODE", BPCQCHEK);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCHEK.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            CEP.TRC(SCCGWA, "ERROR - CHECK CODE");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCHEK.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B08_CHK_HOLI() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIC.DATE);
        CEP.TRC(SCCGWA, BPCSIC.HOLI_CHK_CODE);
        IBS.init(SCCGWA, BPCCCKWD);
        BPCCCKWD.CHECK_DATE = BPCSIC.DATE;
        BPCCCKWD.HCHK_CODE = BPCSIC.HOLI_CHK_CODE;
        IBS.CALLCPN(SCCGWA, "BP-P-USE-CHK-CODE", BPCCCKWD);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCCKWD.RC);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, BPCCCKWD.RC);
        if ((!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) 
            && (!JIBS_tmp_str[1].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CAL_NOTFND))) {
            CEP.TRC(SCCGWA, "ERROR - CHECK CODE");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCCKWD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCCKWD.RC);
        if ((!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CAL_NOTFND)) 
            && BPCCCKWD.HOLIDAY_FLG[1-1] == 'Y') {
            CEP.TRC(SCCGWA, "ERROR - DATE IN HOLIDAY");
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_F_DATE_IS_HOLI);
        }
    }
    public void B09_CHK_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIC.DATE);
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPCSIC.DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            CEP.TRC(SCCGWA, "ERROR - DATE");
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_INVALID);
        }
    }
    public void B10_CHK_BR() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIC.BR);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPCSIC.BR;
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "ERROR - ACCOUNTING CENTRE NOT FOUND");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void B11_CHK_CNTR_TYPE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSIC.CNTR_TYPE);
        IBS.init(SCCGWA, BPCPQPDM);
        BPCPQPDM.PRDT_MODEL = BPCSIC.CNTR_TYPE;
        BPCPQPDM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        S000_CALL_BPZPQPDM();
        if (BPCPQPDM.EXP_DT <= BPCSIC.START_DATE) {
            CEP.TRC(SCCGWA, "ERROR - CTRT TYPE NOT EFFECTIVE");
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CTRT_TYPE_EXPIRY);
        }
    }
    public void B12_CHK_OIC_NO() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_BPZPQPDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-PRD-MODEL", BPCPQPDM);
        if (BPCPQPDM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "ERROR - CTRT TYPE NOT FOUND");
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPDM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
