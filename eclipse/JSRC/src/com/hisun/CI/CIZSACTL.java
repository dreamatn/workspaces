package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSACTL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITACAC_RD;
    boolean pgmRtn = false;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRACAC CIRACAC = new CIRACAC();
    CIRACAC CIRACACO = new CIRACAC();
    CIRACAC CIRACACN = new CIRACAC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSACTL CICSACTL;
    public void MP(SCCGWA SCCGWA, CICSACTL CICSACTL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSACTL = CICSACTL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSACTL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSACTL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MOD_ACAC_INF();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSACTL.DATA.ACAC_NO);
        if (CICSACTL.DATA.ACAC_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "ACAC-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_AC_MUST_INPUT, CICSACTL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_MOD_ACAC_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACAC);
        IBS.init(SCCGWA, CIRACACO);
        IBS.init(SCCGWA, CIRACACN);
        CIRACAC.KEY.ACAC_NO = CICSACTL.DATA.ACAC_NO;
        T000_READ_CITACAC_UPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRACAC.ACAC_STS);
        if (CIRACAC.ACAC_STS != '0') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_STS_CLOSE_E);
        }
        if (CICSACTL.DATA.CTL_POS.equalsIgnoreCase("11")) {
            if (CIRACAC.ACAC_CTL == null) CIRACAC.ACAC_CTL = "";
            JIBS_tmp_int = CIRACAC.ACAC_CTL.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRACAC.ACAC_CTL += " ";
            JIBS_tmp_str[0] = "" + CICSACTL.DATA.FUNC;
            JIBS_tmp_int = JIBS_tmp_str[0].length();
            for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
            CIRACAC.ACAC_CTL = CIRACAC.ACAC_CTL.substring(0, 11 - 1) + JIBS_tmp_str[0] + CIRACAC.ACAC_CTL.substring(11 + 1 - 1);
        }
        T000_REWRITE_CITACAC();
        if (pgmRtn) return;
    }
    public void T000_READ_CITACAC_UPD() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        CITACAC_RD.upd = true;
        IBS.READ(SCCGWA, CIRACAC, CITACAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACAC_INF_NOTFND, CICSACTL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_CITACAC() throws IOException,SQLException,Exception {
        CITACAC_RD = new DBParm();
        CITACAC_RD.TableName = "CITACAC";
        IBS.REWRITE(SCCGWA, CIRACAC, CITACAC_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
