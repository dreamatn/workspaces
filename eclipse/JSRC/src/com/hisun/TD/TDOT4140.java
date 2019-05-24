package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT4140 {
    DBParm TDTSMST_RD;
    DBParm TDTPLED_RD;
    String WS_ERR_MSG = " ";
    String WS_MAIN_AC = " ";
    String WS_CARD_NO = " ";
    int WS_AC_SEQ = 0;
    String WS_AC_NO = " ";
    String WS_RMK = " ";
    char WS_FOUND_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCBVEXC TDCBVEXC = new TDCBVEXC();
    TDCRIMPN TDCRIMPN = new TDCRIMPN();
    TDCPLDT TDCPLDT = new TDCPLDT();
    TDCRPLED TDCRPLED = new TDCRPLED();
    TDRPLED TDRPLED = new TDRPLED();
    TDRSMST TDRSMST = new TDRSMST();
    SCCGWA SCCGWA;
    TDB4140_AWA_4140 TDB4140_AWA_4140;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT4140 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB4140_AWA_4140>");
        TDB4140_AWA_4140 = (TDB4140_AWA_4140) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB4140_AWA_4140.FUNC);
        CEP.TRC(SCCGWA, TDB4140_AWA_4140.BV_TYP);
        if (TDB4140_AWA_4140.FUNC == 'P') {
            B010_CHECK_INPUT();
            B030_EXC_TDZBVEXC_PROC();
        } else if (TDB4140_AWA_4140.FUNC == 'R') {
            B030_EXC_TDZBVEXC_PROC();
        } else if (TDB4140_AWA_4140.FUNC == 'Q') {
            B040_QUARY_PLEDGE();
        } else {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INVALID_OPT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (TDB4140_AWA_4140.AC.trim().length() == 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (TDB4140_AWA_4140.BV_TYP != '0' 
            && TDB4140_AWA_4140.BV_TYP != '1' 
            && TDB4140_AWA_4140.BV_TYP != '2' 
            && TDB4140_AWA_4140.BV_TYP != '3' 
            && TDB4140_AWA_4140.BV_TYP != '4' 
            && TDB4140_AWA_4140.BV_TYP != '5' 
            && TDB4140_AWA_4140.BV_TYP != '6' 
            && TDB4140_AWA_4140.BV_TYP != '7' 
            && TDB4140_AWA_4140.BV_TYP != '9') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_TYP_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B030_EXC_TDZBVEXC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCBVEXC);
        TDCBVEXC.MAIN_AC_O = TDB4140_AWA_4140.AC;
        TDCBVEXC.AC_SEQ_O = TDB4140_AWA_4140.AC_SEQ;
        TDCBVEXC.BOOK_TYP = TDB4140_AWA_4140.BOOK_TYP;
        TDCBVEXC.BOOK_NO = TDB4140_AWA_4140.BOOK_NO;
        TDCBVEXC.BOOK_TOP = TDB4140_AWA_4140.BOOK_TOP;
        TDCBVEXC.ID_TYP_O = TDB4140_AWA_4140.ID_TYP;
        TDCBVEXC.ID_NO_O = TDB4140_AWA_4140.ID_NO;
        TDCBVEXC.DRAW_MTH_O = TDB4140_AWA_4140.DRAW_MTH;
        TDCBVEXC.PSW_O = TDB4140_AWA_4140.PSW;
        TDCBVEXC.REMARK = TDB4140_AWA_4140.REMARK;
        if (TDB4140_AWA_4140.FUNC == 'P') {
            TDCBVEXC.AC_I = TDB4140_AWA_4140.AC;
            TDCBVEXC.INT_STS = TDB4140_AWA_4140.INT_STS;
            TDCBVEXC.AC_O = TDB4140_AWA_4140.AC;
            TDCBVEXC.BV_TYP_O = TDB4140_AWA_4140.BV_TYP;
            TDCBVEXC.BV_NO_O = TDB4140_AWA_4140.BV_NO;
            if (TDB4140_AWA_4140.BV_TYP != '3') {
                TDCBVEXC.BV_TYP_I = TDB4140_AWA_4140.NEW_BVTP;
                TDCBVEXC.BV_NO_I = TDB4140_AWA_4140.NEW_BVNO;
            }
            if (TDB4140_AWA_4140.BV_TYP == '3') {
                TDCBVEXC.BV_TYP_I = TDB4140_AWA_4140.BV_TYP;
                TDCBVEXC.BV_NO_I = TDB4140_AWA_4140.BV_NO;
                TDCBVEXC.CERF_OPT = 'P';
            }
        }
        if (TDB4140_AWA_4140.FUNC == 'R') {
            TDCBVEXC.AC_O = TDCPLDT.AC;
            TDCBVEXC.AC_SEQ_O = TDB4140_AWA_4140.AC_SEQ;
            TDCBVEXC.BV_TYP_I = TDB4140_AWA_4140.BV_TYP;
            TDCBVEXC.BV_TYP_O = TDB4140_AWA_4140.BV_TYP;
            TDCBVEXC.BV_NO_I = TDB4140_AWA_4140.BV_NO;
            TDCBVEXC.BV_NO_O = TDB4140_AWA_4140.BV_NO;
            TDCBVEXC.PLD_NO = TDB4140_AWA_4140.PLD_NO;
            TDCBVEXC.AC_I = TDB4140_AWA_4140.AC;
            TDCBVEXC.MAIN_AC_I = TDCPLDT.MAIN_AC;
            if (TDCPLDT.NEW_BVTYP == '3' 
                && TDCPLDT.BV_TYP == '3') {
                TDCBVEXC.CERF_OPT = 'R';
            }
        }
        CEP.TRC(SCCGWA, TDCBVEXC.CERF_OPT);
        if (TDCBVEXC.CERF_OPT == ' ') {
            TDCBVEXC.CERF_OPT = 'C';
        }
        if (TDB4140_AWA_4140.FUNC == 'P') {
            TDCBVEXC.CERF_OPT = 'P';
        }
        if (TDB4140_AWA_4140.FUNC == 'R') {
            TDCBVEXC.CERF_OPT = 'R';
        }
        CEP.TRC(SCCGWA, TDCBVEXC.CERF_OPT);
        CEP.TRC(SCCGWA, TDCBVEXC.BV_CD_I);
        CEP.TRC(SCCGWA, TDCBVEXC.BV_CD_O);
        S000_CALL_TDZBVEXC();
    }
    public void B040_QUARY_PLEDGE() throws IOException,SQLException,Exception {
        if (TDB4140_AWA_4140.BV_TYP == ' ' 
            && TDB4140_AWA_4140.BV_NO.trim().length() == 0 
            && TDB4140_AWA_4140.PLD_NO.trim().length() == 0 
            && TDB4140_AWA_4140.AC.trim().length() == 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_FIELD_MUST_IPT;
            S000_ERR_MSG_PROC();
        }
        if (TDB4140_AWA_4140.PLD_STS != '1') {
            if (TDB4140_AWA_4140.AC.trim().length() == 0 
                && TDB4140_AWA_4140.PLD_NO.trim().length() == 0) {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_PLD_MUST_IPT;
                S000_ERR_MSG_PROC();
            }
        }
        IBS.init(SCCGWA, TDCBVEXC);
        TDCBVEXC.CERF_OPT = 'Q';
        TDCBVEXC.BV_TYP_I = TDB4140_AWA_4140.BV_TYP;
        TDCBVEXC.BV_NO_I = TDB4140_AWA_4140.BV_NO;
        CEP.TRC(SCCGWA, "F-BUG8");
        CEP.TRC(SCCGWA, TDB4140_AWA_4140.BV_TYP);
        CEP.TRC(SCCGWA, TDB4140_AWA_4140.BV_NO);
        CEP.TRC(SCCGWA, TDB4140_AWA_4140.AC);
        CEP.TRC(SCCGWA, TDB4140_AWA_4140.PLD_NO);
        TDCBVEXC.PLD_NO = TDB4140_AWA_4140.PLD_NO;
        TDCBVEXC.AC_O = TDB4140_AWA_4140.AC;
        TDCBVEXC.PLD_STS = TDB4140_AWA_4140.PLD_STS;
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDB4140_AWA_4140.AC;
        T000_READ_TDTSMST();
        if (TDB4140_AWA_4140.PLD_STS == '1' 
            && TDB4140_AWA_4140.PLD_NO.trim().length() == 0) {
            R00_READ_LED();
            CEP.TRC(SCCGWA, TDRPLED.KEY.PLD_NO);
            TDCBVEXC.PLD_NO = TDRPLED.KEY.PLD_NO;
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.KEY.ACO_AC = TDRPLED.ACO_AC;
            T000_READ_TDTSMST();
        }
        S000_CALL_TDZBVEXC();
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO "
            + "OR ACO_AC = :TDRSMST.KEY.ACO_AC";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void R00_READ_LED() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRPLED);
        CEP.TRC(SCCGWA, TDB4140_AWA_4140.BV_NO);
        TDRPLED.ACO_AC = TDRSMST.KEY.ACO_AC;
        TDRPLED.NEW_BVNO = TDB4140_AWA_4140.NEW_BVNO;
        TDTPLED_RD = new DBParm();
        TDTPLED_RD.TableName = "TDTPLED";
        TDTPLED_RD.where = "ACO_AC = :TDRPLED.ACO_AC "
            + "AND STS = '1'";
        TDTPLED_RD.fst = true;
        IBS.READ(SCCGWA, TDRPLED, this, TDTPLED_RD);
        CEP.TRC(SCCGWA, WS_FOUND_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FOUND_FLG = 'Y';
        }
        CEP.TRC(SCCGWA, WS_FOUND_FLG);
    }
    public void B030_QUARY_PLEDGE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCPLDT);
        IBS.init(SCCGWA, TDCRPLED);
        TDCRPLED.FUNC = '3';
        TDCPLDT.PLD_NO = TDB4140_AWA_4140.PLD_NO;
        TDCPLDT.AC = TDB4140_AWA_4140.AC;
        TDCRPLED.DAT_PTR = TDCPLDT;
        S000_CALL_TDZRPLED();
    }
    public void S000_CALL_TDZRPLED() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-R-TDZRPLED", TDCRPLED);
        if (TDCRPLED.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, TDCRPLED.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_TDZBVEXC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-BV-EXC", TDCBVEXC);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
