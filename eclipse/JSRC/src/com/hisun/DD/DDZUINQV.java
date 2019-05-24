package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUINQV {
    int JIBS_tmp_int;
    DBParm DDTVSABI_RD;
    DBParm DDTMST_RD;
    boolean pgmRtn = false;
    String WS_VS_AC = " ";
    String WS_CCY = " ";
    char WS_CCY_TYP = ' ';
    String CPN_INQ_ACCU = "CI-INQ-ACCU";
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CICACCU CICACCU = new CICACCU();
    DDRVSABI DDRVSABI = new DDRVSABI();
    DDRMST DDRMST = new DDRMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DDCUINQV DDCUINQV;
    public void MP(SCCGWA SCCGWA, DDCUINQV DDCUINQV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUINQV = DDCUINQV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUINQV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DD0000", DDCUINQV.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUINQV.INPUT.VS_AC);
        CEP.TRC(SCCGWA, DDCUINQV.INPUT.CCY);
        CEP.TRC(SCCGWA, DDCUINQV.INPUT.CCY_TYP);
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_INQUIRY_DETAIL();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCUINQV.INPUT.VS_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT, DDCUINQV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUINQV.INPUT.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_M_INPUT, DDCUINQV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DDCUINQV.INPUT.CCY_TYP == ' ') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_CCY_TYP_M_INPUT, DDCUINQV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_INQUIRY_DETAIL() throws IOException,SQLException,Exception {
        WS_VS_AC = DDCUINQV.INPUT.VS_AC;
        WS_CCY = DDCUINQV.INPUT.CCY;
        WS_CCY_TYP = DDCUINQV.INPUT.CCY_TYP;
        T000_READ_DDTVSABI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDRVSABI.KEY.VS_AC;
        T000_READ_DDTMST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = DDRVSABI.KEY.VS_AC;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICACCU.DATA.CI_CNM);
        CEP.TRC(SCCGWA, CICACCU.DATA.AC_CNM);
        B201_TRANS_DETAIL_DATA();
        if (pgmRtn) return;
    }
    public void B201_TRANS_DETAIL_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUINQV.OUTPUT);
        DDCUINQV.OUTPUT.AC_STS = DDRVSABI.AC_STS;
        if (CICACCU.DATA.AC_CNM.trim().length() == 0) {
            DDCUINQV.OUTPUT.P_AC_NAME = CICACCU.DATA.AC_ENM;
        } else {
            DDCUINQV.OUTPUT.P_AC_NAME = CICACCU.DATA.AC_CNM;
        }
        DDCUINQV.OUTPUT.VS_AC_NAME = DDRVSABI.VS_AC_NAME;
        DDCUINQV.OUTPUT.VS_CON_NAME = DDRVSABI.VS_CON_NAME;
        DDCUINQV.OUTPUT.VS_CON_TEL = DDRVSABI.VS_CON_TEL;
        DDCUINQV.OUTPUT.VS_CON_ADDR = DDRVSABI.VS_CON_ADDR;
        DDCUINQV.OUTPUT.OPEN_DT = DDRVSABI.OPEN_DT;
        DDCUINQV.OUTPUT.CLOSE_DT = DDRVSABI.CLOSE_DT;
        DDCUINQV.OUTPUT.REMARK = DDRVSABI.REMARK;
        DDCUINQV.OUTPUT.OPDP_OTH = "" + DDRMST.OPEN_DP;
        JIBS_tmp_int = DDCUINQV.OUTPUT.OPDP_OTH.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) DDCUINQV.OUTPUT.OPDP_OTH = "0" + DDCUINQV.OUTPUT.OPDP_OTH;
        CEP.TRC(SCCGWA, DDCUINQV.INPUT.VS_AC);
        CEP.TRC(SCCGWA, DDCUINQV.OUTPUT.AC_STS);
        CEP.TRC(SCCGWA, DDCUINQV.OUTPUT.P_AC_NAME);
        CEP.TRC(SCCGWA, DDCUINQV.OUTPUT.CINT_FLG);
        CEP.TRC(SCCGWA, DDCUINQV.INPUT.CCY);
        CEP.TRC(SCCGWA, DDCUINQV.INPUT.CCY_TYP);
        CEP.TRC(SCCGWA, DDCUINQV.OUTPUT.VS_CURR_BAL);
        CEP.TRC(SCCGWA, DDCUINQV.OUTPUT.VS_AC_NAME);
        CEP.TRC(SCCGWA, DDCUINQV.OUTPUT.VS_CON_NAME);
        CEP.TRC(SCCGWA, DDCUINQV.OUTPUT.VS_CON_TEL);
        CEP.TRC(SCCGWA, DDCUINQV.OUTPUT.VS_CON_ADDR);
        CEP.TRC(SCCGWA, DDCUINQV.OUTPUT.OPEN_DT);
        CEP.TRC(SCCGWA, DDCUINQV.OUTPUT.CLOSE_DT);
        CEP.TRC(SCCGWA, DDCUINQV.OUTPUT.OPDP_OTH);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_ACCU, CICACCU);
        if (CICACCU.RC.RC_CODE == 0) {
        } else {
            IBS.CPY2CLS(SCCGWA, CICACCU.RC.RC_MMO, DDCUINQV.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTVSABI() throws IOException,SQLException,Exception {
        DDTVSABI_RD = new DBParm();
        DDTVSABI_RD.TableName = "DDTVSABI";
        DDTVSABI_RD.where = "VS_AC = :WS_VS_AC "
            + "AND CCY = :WS_CCY "
            + "AND CCY_TYP = :WS_CCY_TYP";
        IBS.READ(SCCGWA, DDRVSABI, this, DDTVSABI_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_NO_RSLT, DDCUINQV.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTVSABI";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_DDAC_NOT_FND, DDCUINQV.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDRMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
