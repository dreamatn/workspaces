package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5154 {
    DBParm TDTSMST_RD;
    DBParm TDTBVT_RD;
    String WS_ERR_MSG = " ";
    char WS_SMST_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCSCDH TDCSCDH = new TDCSCDH();
    TDRSMST TDRSMST = new TDRSMST();
    TDRBVT TDRBVT = new TDRBVT();
    SCCGWA SCCGWA;
    TDB5154_AWA_5154 TDB5154_AWA_5154;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5154 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5154_AWA_5154>");
        TDB5154_AWA_5154 = (TDB5154_AWA_5154) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5154_AWA_5154.AC_NO);
        CEP.TRC(SCCGWA, TDB5154_AWA_5154.START_DT);
        CEP.TRC(SCCGWA, TDB5154_AWA_5154.END_DT);
        B100_CHECK_INPUT_PROC();
        B200_GET_LIST();
    }
    public void B100_CHECK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (TDB5154_AWA_5154.AC_NO.trim().length() == 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NO_M_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (TDB5154_AWA_5154.START_DT > TDB5154_AWA_5154.END_DT) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_INPUT_DATE_ERROR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, TDB5154_AWA_5154.BV_TYP);
        CEP.TRC(SCCGWA, TDB5154_AWA_5154.BV_NO);
        if (TDB5154_AWA_5154.BV_TYP == '4' 
            && TDB5154_AWA_5154.BV_NO.trim().length() > 0) {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH;
            S000_ERR_MSG_PROC();
        }
        if (TDB5154_AWA_5154.BV_TYP != ' ' 
            && TDB5154_AWA_5154.BV_NO.trim().length() > 0) {
            IBS.init(SCCGWA, TDRSMST);
            TDRSMST.AC_NO = TDB5154_AWA_5154.AC_NO;
            T000_READ_TDTSMST();
            CEP.TRC(SCCGWA, TDRSMST.AC_NO);
            if (WS_SMST_FLG == 'N') {
                WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_EXIST;
                S000_ERR_MSG_PROC();
            }
            CEP.TRC(SCCGWA, TDRSMST.AC_NO);
            if (TDB5154_AWA_5154.BV_NO.trim().length() > 0) {
                IBS.init(SCCGWA, TDRBVT);
                TDRBVT.KEY.AC_NO = TDB5154_AWA_5154.AC_NO;
                TDRBVT.BV_NO = TDB5154_AWA_5154.BV_NO;
                T000_READ_BVT_AC_BV();
                if (!TDB5154_AWA_5154.BV_NO.equalsIgnoreCase(TDRBVT.BV_NO)) {
                    WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_BV_NO_NOT_MATCH;
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void T000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.where = "AC_NO = :TDRSMST.AC_NO";
        IBS.READ(SCCGWA, TDRSMST, this, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_SMST_FLG = 'Y';
        } else {
            WS_SMST_FLG = 'N';
        }
    }
    public void T000_READ_TDTBVT_FIRST() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO";
        TDTBVT_RD.fst = true;
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
    }
    public void T000_READ_BVT_AC_BV() throws IOException,SQLException,Exception {
        TDTBVT_RD = new DBParm();
        TDTBVT_RD.TableName = "TDTBVT";
        TDTBVT_RD.where = "AC_NO = :TDRBVT.KEY.AC_NO "
            + "AND BV_NO = :TDRBVT.BV_NO";
        IBS.READ(SCCGWA, TDRBVT, this, TDTBVT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            T000_READ_TDTBVT_FIRST();
        }
    }
    public void B200_GET_LIST() throws IOException,SQLException,Exception {
        TDCSCDH.AC_NO = TDB5154_AWA_5154.AC_NO;
        TDCSCDH.START_DT = TDB5154_AWA_5154.START_DT;
        TDCSCDH.END_DT = TDB5154_AWA_5154.END_DT;
        TDCSCDH.BV_NO = TDB5154_AWA_5154.BV_NO;
        TDCSCDH.AC_SEQ = TDB5154_AWA_5154.AC_SEQ;
        S000_CALL_TDZSCDH();
    }
    public void S000_CALL_TDZSCDH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-PRO-TDZSCDH", TDCSCDH);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
