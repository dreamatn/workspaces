package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT1260 {
    DBParm TDTGRGP_RD;
    DBParm TDTGPMP_RD;
    DBParm TDTSMST_RD;
    String K_OUTPUT_FMT1 = "TD260";
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCTZZD TDCTZZD = new TDCTZZD();
    DCCURHLD DCCURHLD = new DCCURHLD();
    TDCOTNHD TDCOTNHD = new TDCOTNHD();
    TDRGRGP TDRGRGP = new TDRGRGP();
    TDRGPMP TDRGPMP = new TDRGPMP();
    TDRSMST TDRSMST = new TDRSMST();
    SCCGWA SCCGWA;
    TDB1260_AWA_1260 TDB1260_AWA_1260;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT1260 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB1260_AWA_1260>");
        TDB1260_AWA_1260 = (TDB1260_AWA_1260) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_INFO();
        B030_GET_LIST();
        B040_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_GET_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRGRGP);
        TDRGRGP.KEY.JRNNO = TDB1260_AWA_1260.JRNNO;
        TDRGRGP.KEY.TRD_DT = TDB1260_AWA_1260.TRD_DT;
        CEP.TRC(SCCGWA, TDB1260_AWA_1260.JRNNO);
        CEP.TRC(SCCGWA, TDB1260_AWA_1260.TRD_DT);
        CEP.TRC(SCCGWA, TDRGRGP.KEY.JRNNO);
        CEP.TRC(SCCGWA, TDRGRGP.KEY.TRD_DT);
        S000_READ_TDTGRGP();
        CEP.TRC(SCCGWA, TDRGRGP.AC);
        CEP.TRC(SCCGWA, TDRGRGP.PROVD_NO);
        CEP.TRC(SCCGWA, TDRGRGP.PRD_NO);
        CEP.TRC(SCCGWA, "HLD-NO");
        CEP.TRC(SCCGWA, TDRGRGP.REMARK1);
        IBS.init(SCCGWA, TDRGPMP);
        TDRGPMP.KEY.PROVD_NO = TDRGRGP.PROVD_NO;
        TDRGPMP.KEY.PRD_NO = TDRGRGP.PRD_NO;
        CEP.TRC(SCCGWA, TDRGPMP.KEY.PROVD_NO);
        CEP.TRC(SCCGWA, TDRGPMP.KEY.PRD_NO);
        S000_READ_TDTGPMP();
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.AC_NO = TDRGRGP.AC;
        S000_READ_TDTSMST();
    }
    public void B025_RHLD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCURHLD);
        DCCURHLD.DATA.HLD_NO = TDRGRGP.HLD_NO;
        DCCURHLD.DATA.RHLD_TYP = '1';
        S000_CALL_DCZURHLD();
    }
    public void B030_GET_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCTZZD);
        TDCTZZD.OPT = 'B';
        TDCTZZD.BV_TYP = '4';
        TDCTZZD.CARD_NO = TDB1260_AWA_1260.CARD_NO;
        CEP.TRC(SCCGWA, TDB1260_AWA_1260.CARD_NO);
        TDCTZZD.AC = TDRGRGP.AC;
        CEP.TRC(SCCGWA, TDCTZZD.AC);
        TDCTZZD.NAME = TDB1260_AWA_1260.NAME;
        CEP.TRC(SCCGWA, TDB1260_AWA_1260.NAME);
        TDCTZZD.CCY = TDRSMST.CCY;
        CEP.TRC(SCCGWA, TDRSMST.CCY);
        TDCTZZD.CCY_TYP = TDRSMST.CCY_TYP;
        CEP.TRC(SCCGWA, TDRSMST.CCY_TYP);
        TDCTZZD.FC_CD = TDRSMST.FC_CD;
        CEP.TRC(SCCGWA, TDRSMST.FC_CD);
        TDCTZZD.PBAL = TDRSMST.PBAL;
        CEP.TRC(SCCGWA, TDRSMST.PBAL);
        TDCTZZD.TXN_AMT = TDB1260_AWA_1260.SUR_BAL;
        CEP.TRC(SCCGWA, TDB1260_AWA_1260.SUR_BAL);
        TDCTZZD.DRAW_MTH = '4';
        TDCTZZD.ID_TYP = "10100";
        TDCTZZD.ID_NO = TDB1260_AWA_1260.ID_NO;
        CEP.TRC(SCCGWA, TDB1260_AWA_1260.ID_NO);
        TDCTZZD.INT_FLG = '1';
        TDCTZZD.PVAL_DT = TDRSMST.PVAL_DATE;
        CEP.TRC(SCCGWA, TDRSMST.PVAL_DATE);
        TDCTZZD.CT_FLG = '2';
        TDCTZZD.PRT_FLG = '1';
        TDCTZZD.OPP_CARD_NO = TDB1260_AWA_1260.CARD_NO;
        CEP.TRC(SCCGWA, TDB1260_AWA_1260.CARD_NO);
        TDCTZZD.OPP_NAME = TDB1260_AWA_1260.NAME;
        CEP.TRC(SCCGWA, TDB1260_AWA_1260.NAME);
        TDCTZZD.DRAW_TOT_AMT = TDB1260_AWA_1260.SUR_BAL;
        CEP.TRC(SCCGWA, TDB1260_AWA_1260.SUR_BAL);
        TDCTZZD.BAL = TDRSMST.BAL;
        CEP.TRC(SCCGWA, TDRSMST.PBAL);
        TDCTZZD.VAL_DT = TDRSMST.VAL_DATE;
        CEP.TRC(SCCGWA, TDRSMST.VAL_DATE);
        TDCTZZD.EXP_DT = TDRSMST.EXP_DATE;
        CEP.TRC(SCCGWA, TDRSMST.EXP_DATE);
        TDCTZZD.EXP_INT = 0;
        CEP.TRC(SCCGWA, TDCTZZD.EXP_INT);
        CEP.TRC(SCCGWA, "123");
        S000_CALL_TDZTZZD();
    }
    public void B040_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDCOTNHD);
        TDCOTNHD.CARD_NO = TDB1260_AWA_1260.CARD_NO;
        TDCOTNHD.NAME = TDB1260_AWA_1260.NAME;
        TDCOTNHD.ID_NO = TDB1260_AWA_1260.ID_NO;
        TDCOTNHD.JRNNO = TDB1260_AWA_1260.JRNNO;
        TDCOTNHD.TRD_DT = TDB1260_AWA_1260.TRD_DT;
        TDCOTNHD.SIN_BAL = TDB1260_AWA_1260.SIN_BAL;
        TDCOTNHD.PRICE = TDB1260_AWA_1260.PRICE;
        TDCOTNHD.BAL = TDB1260_AWA_1260.BAL;
        TDCOTNHD.TOTAL = TDB1260_AWA_1260.TOTAL;
        TDCOTNHD.USE_CNT = TDB1260_AWA_1260.USE_CNT;
        TDCOTNHD.TOT_PRI = TDB1260_AWA_1260.TOT_PRI;
        TDCOTNHD.SUR_BAL = TDB1260_AWA_1260.SUR_BAL;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = TDCOTNHD;
        SCCFMT.DATA_LEN = 453;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD, true);
    }
    public void S000_CALL_TDZTZZD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TZZ-DR", TDCTZZD);
    }
    public void S000_READ_TDTGRGP() throws IOException,SQLException,Exception {
        TDTGRGP_RD = new DBParm();
        TDTGRGP_RD.TableName = "TDTGRGP";
        TDTGRGP_RD.where = "JRNNO = :TDRGRGP.KEY.JRNNO "
            + "AND TRD_DT = :TDRGRGP.KEY.TRD_DT";
        IBS.READ(SCCGWA, TDRGRGP, this, TDTGRGP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_READ_TDTGPMP() throws IOException,SQLException,Exception {
        TDTGPMP_RD = new DBParm();
        TDTGPMP_RD.TableName = "TDTGPMP";
        IBS.READ(SCCGWA, TDRGPMP, TDTGPMP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_READ_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_MSGID = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, SCCBINF);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
