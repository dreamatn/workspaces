package com.hisun.GD;

import com.hisun.DD.*;
import com.hisun.TD.*;
import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class GDZIQLDR {
    DBParm GDTPLDR_RD;
    brParm GDTPLDR_BR = new brParm();
    DBParm TDTSMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTMST_RD;
    boolean pgmRtn = false;
    String CPN_DD_S_SIGN_PROC = "DD-S-SIGN-PROC";
    String WS_ERR_MSG = " ";
    char WS_FLG = ' ';
    double WS_PLDR_RELATE_AMT = 0;
    short WS_COUNT = 0;
    short WS_ALL = 0;
    int WS_BAL_CNT = 0;
    int WS_IDX = 0;
    char WS_PLDR_FLG = ' ';
    GDCMSG_ERROR_MSG GDCMSG_ERROR_MSG = new GDCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    CICACCU CICACCU = new CICACCU();
    CICQACAC CICQACAC = new CICQACAC();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    int WS_CNT = 0;
    int WS_VAL_DATE = 0;
    int WS_START_NUM = 0;
    double WS_ALL_RELAT_AMT = 0;
    GDRPLDR GDRPLDR = new GDRPLDR();
    DDRMST DDRMST = new DDRMST();
    TDRSMST TDRSMST = new TDRSMST();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    GDCIQLDR GDCIQLDR;
    public void MP(SCCGWA SCCGWA, GDCIQLDR GDCIQLDR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.GDCIQLDR = GDCIQLDR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "GDZIQLDR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_10_CHECK_AC_STS();
        if (pgmRtn) return;
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_DEPOSIT_PROC();
        if (pgmRtn) return;
    }
    public void B000_10_CHECK_AC_STS() throws IOException,SQLException,Exception {
        if (GDCIQLDR.IN_AC.trim().length() > 0 
            && GDCIQLDR.IN_SEQ == 0) {
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = GDCIQLDR.IN_AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            R000_CHECK_GDKD_PROC();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.init(SCCGWA, TDRSMST);
                TDRSMST.AC_NO = GDCIQLDR.IN_AC;
                T000_READ_TDTSMST_01();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                } else {
                    if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("033")) {
                        WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NOT_GD_AC;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            } else {
                if (DDRMST.AC_TYPE != 'N') {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NOT_GD_AC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        if (GDCIQLDR.IN_AC.trim().length() > 0 
            && GDCIQLDR.IN_SEQ != 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.DATA.AGR_NO = GDCIQLDR.IN_AC;
            CICQACAC.DATA.AGR_SEQ = GDCIQLDR.IN_SEQ;
            CICQACAC.FUNC = 'R';
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NONEX_CONNECT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
                Z_RET();
                if (pgmRtn) return;
            } else {
                if (!TDRSMST.PRDAC_CD.equalsIgnoreCase("033")) {
                    WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_NOT_GD_AC;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (GDCIQLDR.RSEQ.trim().length() > 0 
            && GDCIQLDR.IN_AC.trim().length() == 0 
            && GDCIQLDR.IN_SEQ == 0) {
            WS_FLG = '1';
        }
        if (GDCIQLDR.IN_AC.trim().length() > 0 
            && GDCIQLDR.IN_SEQ != 0) {
            WS_FLG = '2';
        }
    }
    public void B020_DEPOSIT_PROC() throws IOException,SQLException,Exception {
        if (WS_FLG == '1' 
            || WS_FLG == '2') {
            B025_PROC_BROWSE();
            if (pgmRtn) return;
        }
    }
    public void B025_PROC_BROWSE() throws IOException,SQLException,Exception {
        if (WS_FLG == '1') {
            T000_STARTBR_GDTPLDR();
            if (pgmRtn) return;
        }
        if (WS_FLG == '2') {
            T000_STARTBR_GDTPLDR_1();
            if (pgmRtn) return;
        }
        T000_READNEXT_GDTPLDR();
        if (pgmRtn) return;
        if (WS_PLDR_FLG == 'N') {
            WS_ERR_MSG = GDCMSG_ERROR_MSG.GD_PLDR_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_PLDR_FLG != 'N') {
            WS_IDX = 0;
            while (WS_PLDR_FLG != 'N') {
                B000_WRITE_OUTPUT();
                if (pgmRtn) return;
                T000_READNEXT_GDTPLDR();
                if (pgmRtn) return;
            }
            if (WS_PLDR_FLG == 'N') {
                R000_GROUP_ALL_AMT();
                if (pgmRtn) return;
                WS_BAL_CNT = WS_IDX;
            } else {
                R000_GROUP_ALL();
                if (pgmRtn) return;
            }
        }
        T000_ENDBR_GDTPLDR();
        if (pgmRtn) return;
    }
    public void R000_CHECK_GDKD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCIQLDR.IN_AC;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        T000_READ_DDTCCY();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        DDCIQPRD.INPUT_DATA.CCY = CICQACAC.O_DATA.O_ACAC_DATA.O_CCY_ACAC;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
    }
    public void B000_WRITE_OUTPUT() throws IOException,SQLException,Exception {
        WS_IDX += 1;
        IBS.init(SCCGWA, GDCIQLDR.INFO[WS_IDX-1]);
        if (WS_FLG == '1') {
            GDCIQLDR.RSEQ = GDRPLDR.KEY.RSEQ;
        }
        CEP.TRC(SCCGWA, GDRPLDR.RELAT_AMT);
        GDCIQLDR.TAMT += GDRPLDR.RELAT_AMT;
        CEP.TRC(SCCGWA, GDCIQLDR.TAMT);
        GDCIQLDR.INFO[WS_IDX-1].OUT_AC = GDRPLDR.KEY.AC;
        if (GDRPLDR.KEY.AC_SEQ != 0) {
            GDCIQLDR.INFO[WS_IDX-1].OUT_SEQ = GDRPLDR.KEY.AC_SEQ;
        }
        GDCIQLDR.INFO[WS_IDX-1].CCY = GDRPLDR.CCY;
        GDCIQLDR.INFO[WS_IDX-1].HOLD_STS = GDRPLDR.RELAT_STS;
        if (GDRPLDR.AC_TYP == '0') {
            if ((SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
                || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
                GDCIQLDR.INFO[WS_IDX-1].OUT_AC = GDRPLDR.KEY.AC;
            }
            IBS.init(SCCGWA, DDRMST);
            DDRMST.KEY.CUS_AC = GDRPLDR.KEY.AC;
            T000_READ_DDTMST();
            if (pgmRtn) return;
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = GDRPLDR.KEY.AC;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDRCCY);
            DDRCCY.KEY.AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_DDTCCY();
            if (pgmRtn) return;
            GDCIQLDR.INFO[WS_IDX-1].AC_TYP = GDRPLDR.AC_TYP;
            GDCIQLDR.INFO[WS_IDX-1].BR = GDRPLDR.RELS_BR;
        }
        if (GDRPLDR.AC_TYP == '1' 
            && GDRPLDR.KEY.AC_SEQ == 0) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = GDRPLDR.KEY.AC;
            T000_READ_TDTSMST_01();
            if (pgmRtn) return;
            GDCIQLDR.INFO[WS_IDX-1].OUT_AC = TDRSMST.AC_NO;
            GDCIQLDR.INFO[WS_IDX-1].AC_TYP = GDRPLDR.AC_TYP;
            GDCIQLDR.INFO[WS_IDX-1].BR = GDRPLDR.RELS_BR;
        }
        GDCIQLDR.INFO[WS_IDX-1].CDT = GDRPLDR.RELS_DATE;
        GDCIQLDR.INFO[WS_IDX-1].CTM = GDRPLDR.RELS_TIME;
        GDCIQLDR.INFO[WS_IDX-1].RAMT = GDRPLDR.RELAT_AMT;
        GDCIQLDR.TCNT = WS_IDX;
        if (GDRPLDR.AC_TYP == '1' 
            && GDRPLDR.KEY.AC_SEQ != 0) {
            IBS.init(SCCGWA, CICQACAC);
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_NO = GDRPLDR.KEY.AC;
            CICQACAC.DATA.AGR_SEQ = GDRPLDR.KEY.AC_SEQ;
            S000_CALL_CIZQACAC();
            if (pgmRtn) return;
            TDRSMST.KEY.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
            T000_READ_TDTSMST();
            if (pgmRtn) return;
        }
        GDCIQLDR.INFO[WS_IDX-1].CDT = GDRPLDR.CRT_DATE;
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_GROUP_ALL_AMT() throws IOException,SQLException,Exception {
        if (WS_FLG == '2') {
            T000_02_GROUP_AMT_PROC();
            if (pgmRtn) return;
            GDCIQLDR.TAMT = WS_ALL_RELAT_AMT;
        }
    }
    public void R000_GROUP_ALL() throws IOException,SQLException,Exception {
        if (WS_FLG == '2') {
            T000_02_GROUP_AMT_PROC_1();
            if (pgmRtn) return;
            T000_01_GROUP_REC_PROC_1();
            if (pgmRtn) return;
            GDCIQLDR.TAMT = WS_ALL_RELAT_AMT;
        }
    }
    public void T000_ENDBR_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, GDTPLDR_BR);
    }
    public void T000_02_GROUP_AMT_PROC() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "RSEQ = :GDRPLDR.KEY.RSEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-ALL-RELAT-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_02_GROUP_AMT_PROC_1() throws IOException,SQLException,Exception {
        GDRPLDR.KEY.AC = GDCIQLDR.IN_AC;
        GDRPLDR.KEY.AC_SEQ = GDCIQLDR.IN_SEQ;
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-ALL-RELAT-AMT=IFNULL(SUM(RELAT_AMT),0)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_01_GROUP_REC_PROC_1() throws IOException,SQLException,Exception {
        GDTPLDR_RD = new DBParm();
        GDTPLDR_RD.TableName = "GDTPLDR";
        GDTPLDR_RD.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_RD.set = "WS-CNT=COUNT(*)";
        IBS.GROUP(SCCGWA, GDRPLDR, this, GDTPLDR_RD);
    }
    public void T000_STARTBR_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        WS_PLDR_FLG = 'N';
        GDRPLDR.KEY.RSEQ = GDCIQLDR.RSEQ;
        GDRPLDR.RELAT_STS = 'N';
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "RSEQ = :GDRPLDR.KEY.RSEQ "
            + "AND RELAT_STS = :GDRPLDR.RELAT_STS";
        GDTPLDR_BR.rp.order = "AC_TYP,CRT_DATE,RELAT_TIME";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
    }
    public void T000_READNEXT_GDTPLDR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PLDR_FLG = 'Y';
        } else {
            WS_PLDR_FLG = 'N';
        }
    }
    public void T000_STARTBR_GDTPLDR_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDRPLDR);
        WS_PLDR_FLG = 'N';
        GDRPLDR.KEY.AC = GDCIQLDR.IN_AC;
        GDRPLDR.KEY.AC_SEQ = GDCIQLDR.IN_SEQ;
        GDTPLDR_BR.rp = new DBParm();
        GDTPLDR_BR.rp.TableName = "GDTPLDR";
        GDTPLDR_BR.rp.where = "AC = :GDRPLDR.KEY.AC "
            + "AND AC_SEQ = :GDRPLDR.KEY.AC_SEQ";
        GDTPLDR_BR.rp.order = "CRT_DATE,RELAT_TIME";
        IBS.STARTBR(SCCGWA, GDRPLDR, this, GDTPLDR_BR);
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_READ_TDTSMST_01() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
    }
    public void T000_READ_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
