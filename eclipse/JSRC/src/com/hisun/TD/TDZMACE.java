package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCCSUBS;
import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class TDZMACE {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm TDTCMST_RD;
    DBParm TDTSMST_RD;
    DBParm TDTBVT_RD;
    boolean pgmRtn = false;
    String K_INQ_MACE_FMT = "TD523";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_PROD_CD = " ";
    char WS_BVT_FLG = ' ';
    TDZMACE_WS_TABLES_INFO WS_TABLES_INFO = new TDZMACE_WS_TABLES_INFO();
    char WS_IAMST_FLG = ' ';
    TDZMACE_WS_OUT_INF WS_OUT_INF = new TDZMACE_WS_OUT_INF();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCSUBS SCCSUBS = new SCCSUBS();
    TDRBVT TDRBVT = new TDRBVT();
    DCRIAMST DCRIAMST = new DCRIAMST();
    DCRIAACR DCRIAACR = new DCRIAACR();
    TDRSMST TDRSMST = new TDRSMST();
    TDRCDI TDRCDI = new TDRCDI();
    TDCOIMAE TDCOIMAE = new TDCOIMAE();
    CICACCU CICACCU = new CICACCU();
    DCCPACTY DCCPACTY = new DCCPACTY();
    TDRSTS TDRSTS = new TDRSTS();
    CICCUST CICCUST = new CICCUST();
    TDRCMST TDRCMST = new TDRCMST();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    TDCOMACE TDCOMACE = new TDCOMACE();
    CICQACRI CICQACRI = new CICQACRI();
    SCCCLDT SCCCLDT = new SCCCLDT();
    CICQACRL CICQACRL = new CICQACRL();
    SCCGWA SCCGWA;
    TDCMACE TDCMACE;
    public void MP(SCCGWA SCCGWA, TDCMACE TDCMACE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCMACE = TDCMACE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "TDZMACE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCMACE.OUTPUT_DATA);
        IBS.init(SCCGWA, TDRBVT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT();
        if (pgmRtn) return;
        S100_CALL_CIZCUST();
        if (pgmRtn) return;
        if (TDCMACE.INPUT_DATA.BV_TYPE != '0' 
            && TDRCMST.BV_TYP != '0') {
            T000_READ_TDTBVT();
            if (pgmRtn) return;
        }
        B110_OUT_INF();
        if (pgmRtn) return;
    }
    public void B100_CHK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRCMST);
        TDRCMST.KEY.AC_NO = TDCMACE.INPUT_DATA.AC_NO;
        T100_READ_TDTCMST();
        if (pgmRtn) return;
        if (TDRCMST.BV_TYP != TDCMACE.INPUT_DATA.BV_TYPE 
            && TDCMACE.INPUT_DATA.BV_TYPE != ' ') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_BV_TYP_NOT_MATCH);
        }
        IBS.init(SCCGWA, CICQACRI);
        CICQACRI.FUNC = 'A';
        CICQACRI.DATA.AGR_NO = TDCMACE.INPUT_DATA.AC_NO;
        S000_CALL_CIZQACRI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICQACRL);
        B230_PROD_AC_REL();
        if (pgmRtn) return;
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.AC_NO = TDCMACE.INPUT_DATA.AC_NO;
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_REL);
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
    }
    public void B230_PROD_AC_REL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        B231_GET_PRD_INF();
        if (pgmRtn) return;
    }
    public void B231_GET_PRD_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRCMST.PROD_CD);
        if (TDRCMST.PROD_CD.trim().length() == 0) {
            TDRSMST.AC_NO = TDRCMST.KEY.AC_NO;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            WS_PROD_CD = TDRSMST.PROD_CD;
        } else {
            WS_PROD_CD = TDRCMST.PROD_CD;
        }
        CEP.TRC(SCCGWA, WS_PROD_CD);
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = WS_PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("043")) {
            CICQACRL.DATA.AC_REL = "05";
        } else {
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("044")) {
                CICQACRL.DATA.AC_REL = "06";
            }
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("021") 
                || BPCPQPRD.AC_TYPE.equalsIgnoreCase("037")) {
                CICQACRL.DATA.AC_REL = "07";
            }
            if (BPCPQPRD.AC_TYPE.equalsIgnoreCase("045")) {
                CICQACRL.DATA.AC_REL = "11";
            }
        }
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_REL);
    }
    public void B110_OUT_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRCMST.KEY.AC_NO);
        CEP.TRC(SCCGWA, TDRCMST.STS);
        CEP.TRC(SCCGWA, TDRCMST.STSW);
        CEP.TRC(SCCGWA, TDRBVT.BV_CD);
        CEP.TRC(SCCGWA, TDRBVT.BV_NO);
        CEP.TRC(SCCGWA, TDRBVT.STSW);
        CEP.TRC(SCCGWA, TDRCMST.DRAW_MTH);
        CEP.TRC(SCCGWA, TDRCMST.CROS_DR_FLG);
        CEP.TRC(SCCGWA, TDRCMST.CROS_CR_FLG);
        CEP.TRC(SCCGWA, TDRCMST.OWNER_BRDP);
        CEP.TRC(SCCGWA, TDRCMST.CHE_BR);
        CEP.TRC(SCCGWA, TDRCMST.OPEN_TLR);
        CEP.TRC(SCCGWA, TDRCMST.OPEN_DATE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_TYPE);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_ID_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_ENM);
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRCMST.STSW.substring(2 - 1, 2 + 1 - 1));
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        CEP.TRC(SCCGWA, TDRCMST.STSW.substring(3 - 1, 3 + 1 - 1));
        CEP.TRC(SCCGWA, TDRCMST.OWNER_BR);
        CEP.TRC(SCCGWA, TDRCMST.FRG_IND);
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
        TDCOMACE.OIMAE_AC = TDRCMST.KEY.AC_NO;
        TDCOMACE.OIMAE_AC_STS = TDRCMST.STS;
        TDCOMACE.OIMAE_AC_STSW = TDRCMST.STSW;
        TDCOMACE.OIMAE_PROD_CD = TDRCMST.PROD_CD;
        TDCOMACE.OIMAE_CCY = TDRCMST.CCY;
        TDCOMACE.OIMAE_BV_CD = TDRBVT.BV_CD;
        TDCOMACE.OIMAE_BV_NO = TDRBVT.BV_NO;
        TDCOMACE.OIMAE_BV_STSW = TDRBVT.STSW;
        TDCOMACE.OIMAE_DRAW_MTH = TDRCMST.DRAW_MTH;
        TDCOMACE.OIMAE_DR_FLG = TDRCMST.CROS_DR_FLG;
        TDCOMACE.OIMAE_CR_FLG = TDRCMST.CROS_CR_FLG;
        TDCOMACE.OIMAE_AC_BK = "" + TDRCMST.OWNER_BRDP;
        JIBS_tmp_int = TDCOMACE.OIMAE_AC_BK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) TDCOMACE.OIMAE_AC_BK = "0" + TDCOMACE.OIMAE_AC_BK;
        TDCOMACE.OIMAE_AC_BR = TDRCMST.CHE_BR;
        TDCOMACE.OIMAE_OPEN_TLR = TDRCMST.OPEN_TLR;
        TDCOMACE.OIMAE_OPEN_DATE = TDRCMST.OPEN_DATE;
        TDCOMACE.OIMAE_ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
        TDCOMACE.OIMAE_ID_NO = CICCUST.O_DATA.O_ID_NO;
        TDCOMACE.OIMAE_CI_NO = CICCUST.O_DATA.O_CI_NO;
        TDCOMACE.OIMAE_CI_CNM = CICQACRI.O_DATA.O_AC_CNM;
        TDCOMACE.OIMAE_AC_CNM = CICCUST.O_DATA.O_CI_NM;
        TDCOMACE.OIMAE_AC_ENM = CICCUST.O_DATA.O_CI_ENM;
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        TDCOMACE.OIMAE_HLD_TYP1 = TDRCMST.STSW.substring(2 - 1, 2 + 1 - 1).charAt(0);
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        TDCOMACE.OIMAE_HLD_TYP2 = TDRCMST.STSW.substring(3 - 1, 3 + 1 - 1).charAt(0);
        TDCOMACE.OIMAE_OPEN_BR = TDRCMST.OWNER_BR;
        TDCOMACE.OIMAE_FRG_IND = TDRCMST.FRG_IND;
        TDCOMACE.REF_TYP = TDRCMST.REF_TYP;
        TDCOMACE.INT_METH = TDRCMST.INT_METH;
        TDCOMACE.OIC_NO = TDRCMST.OIC_NO;
        TDCOMACE.RESP_CD = TDRCMST.RESP_CD;
        TDCOMACE.SUB_DP = TDRCMST.SUB_DP;
        TDCOMACE.CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        TDCOMACE.REL_AC_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        TDCMACE.OUTPUT_DATA.OIMAE_AC = TDRCMST.KEY.AC_NO;
        TDCMACE.OUTPUT_DATA.OIMAE_AC_STS = TDRCMST.STS;
        TDCMACE.O_BV_TYP = TDRCMST.BV_TYP;
        TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW = TDRCMST.STSW;
        TDCMACE.OUTPUT_DATA.OIMAE_PROD_CD = TDRCMST.PROD_CD;
        TDCMACE.OUTPUT_DATA.OIMAE_CCY = TDRCMST.CCY;
        TDCMACE.OUTPUT_DATA.OIMAE_BV_CD = TDRBVT.BV_CD;
        TDCMACE.OUTPUT_DATA.OIMAE_BV_NO = TDRBVT.BV_NO;
        TDCMACE.OUTPUT_DATA.OIMAE_BV_STSW = TDRBVT.STSW;
        TDCMACE.OUTPUT_DATA.OIMAE_DRAW_MTH = TDRCMST.DRAW_MTH;
        TDCMACE.OUTPUT_DATA.OIMAE_DR_FLG = TDRCMST.CROS_DR_FLG;
        TDCMACE.OUTPUT_DATA.OIMAE_CR_FLG = TDRCMST.CROS_CR_FLG;
        TDCMACE.OUTPUT_DATA.OIMAE_AC_BK = "" + TDRCMST.OWNER_BRDP;
        JIBS_tmp_int = TDCMACE.OUTPUT_DATA.OIMAE_AC_BK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) TDCMACE.OUTPUT_DATA.OIMAE_AC_BK = "0" + TDCMACE.OUTPUT_DATA.OIMAE_AC_BK;
        TDCMACE.OUTPUT_DATA.OIMAE_AC_BR = TDRCMST.CHE_BR;
        TDCMACE.OUTPUT_DATA.OIMAE_OPEN_TLR = TDRCMST.OPEN_TLR;
        TDCMACE.OUTPUT_DATA.OIMAE_OPEN_DATE = TDRCMST.OPEN_DATE;
        TDCMACE.OUTPUT_DATA.OIMAE_ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
        TDCMACE.OUTPUT_DATA.OIMAE_ID_NO = CICCUST.O_DATA.O_ID_NO;
        TDCMACE.OUTPUT_DATA.OIMAE_CI_NO = CICCUST.O_DATA.O_CI_NO;
        TDCMACE.OUTPUT_DATA.OIMAE_CI_CNM = CICQACRI.O_DATA.O_AC_CNM;
        TDCMACE.OUTPUT_DATA.OIMAE_AC_CNM = CICCUST.O_DATA.O_CI_NM;
        TDCMACE.OUTPUT_DATA.OIMAE_AC_ENM = CICCUST.O_DATA.O_CI_ENM;
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        TDCMACE.OUTPUT_DATA.OIMAE_HLD_TYP1 = TDRCMST.STSW.substring(2 - 1, 2 + 1 - 1).charAt(0);
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        TDCMACE.OUTPUT_DATA.OIMAE_HLD_TYP2 = TDRCMST.STSW.substring(3 - 1, 3 + 1 - 1).charAt(0);
        TDCMACE.OUTPUT_DATA.OIMAE_OPEN_BR = TDRCMST.OWNER_BR;
        TDCMACE.OUTPUT_DATA.OIMAE_FRG_IND = TDRCMST.FRG_IND;
        TDCMACE.OUTPUT_DATA.REF_TYP = TDRCMST.REF_TYP;
        TDCMACE.OUTPUT_DATA.INT_METH = TDRCMST.INT_METH;
        TDCMACE.OUTPUT_DATA.OIC_NO = TDRCMST.OIC_NO;
        TDCMACE.OUTPUT_DATA.RESP_CD = TDRCMST.RESP_CD;
        TDCMACE.OUTPUT_DATA.SUB_DP = TDRCMST.SUB_DP;
        TDCMACE.OUTPUT_DATA.CI_TYP = CICCUST.O_DATA.O_CI_TYP;
        TDCMACE.OUTPUT_DATA.REL_AC_NO = CICQACRL.O_DATA.O_REL_AC_NO;
        CEP.TRC(SCCGWA, TDRCMST.OPT_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        if (TDRCMST.STSW == null) TDRCMST.STSW = "";
        JIBS_tmp_int = TDRCMST.STSW.length();
        for (int i=0;i<99-JIBS_tmp_int;i++) TDRCMST.STSW += " ";
        if (TDRCMST.STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && TDRCMST.OPT_DT < SCCGWA.COMM_AREA.AC_DATE) {
            if (TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW == null) TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW = "";
            JIBS_tmp_int = TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW += " ";
            TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW = TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW.substring(0, 4 - 1) + "0" + TDCMACE.OUTPUT_DATA.OIMAE_AC_STSW.substring(4 + 1 - 1);
            if (TDCOMACE.OIMAE_AC_STSW == null) TDCOMACE.OIMAE_AC_STSW = "";
            JIBS_tmp_int = TDCOMACE.OIMAE_AC_STSW.length();
            for (int i=0;i<32-JIBS_tmp_int;i++) TDCOMACE.OIMAE_AC_STSW += " ";
            TDCOMACE.OIMAE_AC_STSW = TDCOMACE.OIMAE_AC_STSW.substring(0, 4 - 1) + "0" + TDCOMACE.OIMAE_AC_STSW.substring(4 + 1 - 1);
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_INQ_MACE_FMT;
        SCCFMT.DATA_PTR = TDCOMACE;
        SCCFMT.DATA_LEN = 1083;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S100_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = TDCMACE.INPUT_DATA.AC_NO;
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        if (CICQACRI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0 
            && CICQACRL.RC.RC_CODE != 8054) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
    }
    public void S000_CALL_BPZPQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-PRDT-INFO", BPCPQPRD);
        if (BPCPQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQPRD.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T100_READ_TDTCMST() throws IOException,SQLException,Exception {
        TDTCMST_RD = new DBParm();
        TDTCMST_RD.TableName = "TDTCMST";
        IBS.READ(SCCGWA, TDRCMST, TDTCMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_NOTFND);
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO";
        TDTSMST_RD.fst = true;
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, TDRSMST.AC_NO);
            CEP.TRC(SCCGWA, TDRSMST.PROD_CD);
        } else {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST);
        }
    }
    public void T000_READ_TDTBVT() throws IOException,SQLException,Exception {
        TDRBVT.KEY.AC_NO = TDRCMST.KEY.AC_NO;
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_REC_NOTFND);
        }
    }
    public void T000_READ_TDTBVT1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDRBVT.KEY.AC_NO);
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        IBS.READ(SCCGWA, TDRBVT, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BVT_FLG = 'N';
        } else {
            WS_BVT_FLG = 'Y';
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (TDCMACE.OUTPUT_DATA.RC_MSG.RC_CD != 0) {
            CEP.TRC(SCCGWA, "TDCMACE=");
            CEP.TRC(SCCGWA, TDCMACE);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
