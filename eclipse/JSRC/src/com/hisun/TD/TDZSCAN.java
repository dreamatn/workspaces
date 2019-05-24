package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.BP.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDZSCAN {
    int JIBS_tmp_int;
    DBParm TDTCMST_RD;
    DBParm TDTSMST_RD;
    String JIBS_tmp_str[] = new String[10];
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_CI_TYP = ' ';
    short WS_RC = 0;
    short WS_RC_DISP = 0;
    long WS_JRNNO = 0;
    String WS_REL_AC = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    CICCUST CICCUST = new CICCUST();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDCMACO TDCMACO = new TDCMACO();
    TDCTZZC TDCTZZC = new TDCTZZC();
    TDCTTZC TDCTTZC = new TDCTTZC();
    TDCTDHC TDCTDHC = new TDCTDHC();
    TDCTXYC TDCTXYC = new TDCTXYC();
    TDCOOADV TDCOOADV = new TDCOOADV();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    TDCBVCD TDCBVCD = new TDCBVCD();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    TDCTCDEC TDCTCDEC = new TDCTCDEC();
    SCCCLDT SCCCLDT = new SCCCLDT();
    TDCKHCR TDCKHCR = new TDCKHCR();
    TDRCMST TDRCMST = new TDRCMST();
    TDRSMST TDRSMST = new TDRSMST();
    DCCURHLD DCCURHLD = new DCCURHLD();
    CICQACRL CICQACRL = new CICQACRL();
    CICACCU CICACCU = new CICACCU();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    TDCSCAN TDCSCAN;
    public void MP(SCCGWA SCCGWA, TDCSCAN TDCSCAN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCSCAN = TDCSCAN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZSCAN return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_REL_HLD();
        B010_GET_CI_INFO();
        B020_GET_PROD_MODEL_PROC();
        B030_TRANS_DATA_PROC();
    }
    public void B000_REL_HLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCSCAN.AC_NO;
        T000_READ_TDTCMST_UPD();
        CEP.TRC(SCCGWA, TDRCMST.STS);
        if (TDRCMST.STS != '3') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_ERR_CANCEL;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (TDRCMST.RMK_100 == null) TDRCMST.RMK_100 = "";
        JIBS_tmp_int = TDRCMST.RMK_100.length();
        for (int i=0;i<200-JIBS_tmp_int;i++) TDRCMST.RMK_100 += " ";
        CEP.TRC(SCCGWA, TDRCMST.RMK_100.substring(50 - 1, 50 + 12 - 1));
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = TDCSCAN.ACO_AC;
        T000_READU_SMST();
        CEP.TRC(SCCGWA, TDRSMST.BUSI_NO);
        IBS.init(SCCGWA, DCCURHLD);
        DCCURHLD.DATA.HLD_NO = TDRSMST.BUSI_NO;
        DCCURHLD.DATA.RHLD_TYP = '2';
        DCCURHLD.DATA.RAMT = TDRSMST.BAL;
        DCCURHLD.DATA.HLD_TYP = '2';
        S000_CALL_DCZURHLD();
        SCCGWA.COMM_AREA.CANCEL_IND = 'Y';
    }
    public void B010_GET_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = TDCSCAN.CI_NO;
        CICCUST.FUNC = 'C';
        S000_LINK_CIZCUST();
        WS_CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        CEP.TRC(SCCGWA, WS_CI_TYP);
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = TDCSCAN.AC_NO;
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
        CICQACRL.DATA.AC_REL = "13";
        S000_CALL_CIZQACRL();
        WS_REL_AC = CICQACRL.O_DATA.O_REL_AC_NO;
        CEP.TRC(SCCGWA, WS_REL_AC);
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_REL_AC;
        S000_CALL_CIZACCU();
    }
    public void B010_TRANS_MACO_PROC_CANCEL() throws IOException,SQLException,Exception {
    }
    public void B010_TRANS_MACO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
    }
    public void B020_GET_PROD_MODEL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        CEP.TRC(SCCGWA, TDCSCAN.PROD_CD);
        BPCPQPRD.PRDT_CODE = TDCSCAN.PROD_CD;
        S000_CALL_BPZPQPRD();
    }
    public void B030_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCKHCR);
        TDCKHCR.PROD_CD = TDCSCAN.PROD_CD;
        TDCKHCR.CI_NO = TDCSCAN.CI_NO;
        TDCKHCR.AC = TDCSCAN.AC_NO;
        TDCKHCR.GACO_AC = TDCSCAN.ACO_AC;
        TDCKHCR.GAC_NO = TDCSCAN.AC_NO;
        if (TDRCMST.RMK_100 == null) TDRCMST.RMK_100 = "";
        JIBS_tmp_int = TDRCMST.RMK_100.length();
        for (int i=0;i<200-JIBS_tmp_int;i++) TDRCMST.RMK_100 += " ";
        if (TDRCMST.RMK_100.substring(50 - 1, 50 + 12 - 1).trim().length() == 0) TDCKHCR.JRNNO = 0;
        else TDCKHCR.JRNNO = Long.parseLong(TDRCMST.RMK_100.substring(50 - 1, 50 + 12 - 1));
        TDCKHCR.VAL_DT = TDCSCAN.VAL_DT;
        TDCKHCR.TXN_AMT = TDRSMST.BAL;
        TDCKHCR.OPP_AC = WS_REL_AC;
        TDCKHCR.ACTI_NO = TDRSMST.ACTI_NO;
        if (CICACCU.DATA.FRM_APP.equalsIgnoreCase("DC")) {
            TDCKHCR.OPP_BV = '1';
        }
        S000_CALL_TDZKHCR();
    }
    public void T000_READ_TDTCMST_UPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        TDTCMST_RD.where = "AC_NO = :TDRCMST.KEY.AC_NO";
        TDTCMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRCMST, this, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READU_SMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        CEP.TRC(SCCGWA, CICACCU.RC);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_TDZTCDEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TCDE-CR", TDCTCDEC);
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE);
    }
    public void S000_LINK_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_TDZMACO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-MACO-CR", TDCMACO);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_TDZKHCR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-KH-CR", TDCKHCR);
    }
    public void S000_CALL_TDZTDHC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TDH-CR", TDCTDHC);
    }
    public void S000_CALL_TDZTTZC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TTZ-CR", TDCTTZC);
    }
    public void S000_CALL_TDZTXYC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TXY-CR", TDCTXYC);
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        WS_RC = 0;
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (WS_RC != 0) {
            SCCCLDT.RC = WS_RC;
        }
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        if (SCCCLDT.RC != 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_OTHER_ERR);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
