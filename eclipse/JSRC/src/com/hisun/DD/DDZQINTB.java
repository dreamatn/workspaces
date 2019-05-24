package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZQINTB {
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTINTB_RD;
    boolean pgmRtn = false;
    String WS_AGR_NO = " ";
    char WS_ACAC_FLG = ' ';
    char WS_ACRL_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    DDRINTB DDRINTB = new DDRINTB();
    CICQACAC CICQACAC = new CICQACAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCQINTB DDCQINTB;
    public void MP(SCCGWA SCCGWA, DDCQINTB DDCQINTB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCQINTB = DDCQINTB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZQINTB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DDCQINTB.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCQINTB.FUNC);
        if (DDCQINTB.FUNC == 'I') {
            B020_INQ_INTB_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_FUNC_INVALID, CICQACAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCQINTB.FUNC == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT);
        }
        if (DDCQINTB.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        if (DDCQINTB.CCY.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT);
        }
        if (DDCQINTB.CCY_TYPE == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT);
        }
    }
    public void B020_INQ_INTB_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCQINTB.FUNC);
        CEP.TRC(SCCGWA, DDCQINTB.AC_NO);
        CEP.TRC(SCCGWA, DDCQINTB.CCY);
        CEP.TRC(SCCGWA, DDCQINTB.CCY_TYPE);
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'C';
        CICQACAC.DATA.AGR_NO = DDCQINTB.AC_NO;
        CICQACAC.DATA.CCY_ACAC = DDCQINTB.CCY;
        CICQACAC.DATA.CR_FLG = DDCQINTB.CCY_TYPE;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDRINTB.KEY.TYPE = 'O';
        T000_READ_DDTINTB();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            DDCQINTB.O_TOD_INT = DDRINTB.DEP_ACCU_INT;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCQINTB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTINTB() throws IOException,SQLException,Exception {
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
