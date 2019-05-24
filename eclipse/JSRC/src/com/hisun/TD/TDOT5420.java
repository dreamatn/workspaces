package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class TDOT5420 {
    String WS_MSGID = " ";
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCBINF SCCBINF = new SCCBINF();
    TDCPRAM TDCPRAM = new TDCPRAM();
    SCCGWA SCCGWA;
    TDB5420_AWA_5420 TDB5420_AWA_5420;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDOT5420 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "TDB5420_AWA_5420>");
        TDB5420_AWA_5420 = (TDB5420_AWA_5420) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MAIN_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5420_AWA_5420.FUNC);
        if (TDB5420_AWA_5420.FUNC != 'I' 
            && TDB5420_AWA_5420.FUNC != 'A' 
            && TDB5420_AWA_5420.FUNC != 'M' 
            && TDB5420_AWA_5420.FUNC != 'D' 
            && TDB5420_AWA_5420.FUNC != 'O') {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_FUNC_ERR);
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, TDB5420_AWA_5420.FUNC);
        IBS.init(SCCGWA, TDCPRAM);
        TDCPRAM.FUNC = TDB5420_AWA_5420.FUNC;
        TDCPRAM.PROD_CD = TDB5420_AWA_5420.PROD_CD;
        CEP.TRC(SCCGWA, TDB5420_AWA_5420.PROD_CD);
        TDCPRAM.ACTI_NO = TDB5420_AWA_5420.ACTI_NO;
        TDCPRAM.ACTI_NAME = TDB5420_AWA_5420.NM;
        TDCPRAM.CCY = TDB5420_AWA_5420.CCY;
        TDCPRAM.HD_FLG = TDB5420_AWA_5420.HD_FLG;
        TDCPRAM.BR = TDB5420_AWA_5420.BR;
        TDCPRAM.SHX_DT = TDB5420_AWA_5420.SHX_DT;
        TDCPRAM.SHI_DT = TDB5420_AWA_5420.SHI_DT;
        TDCPRAM.SDT = TDB5420_AWA_5420.SDT;
        TDCPRAM.DDT = TDB5420_AWA_5420.DDT;
        TDCPRAM.MIN_BAL = TDB5420_AWA_5420.MIN_BAL;
        TDCPRAM.MAX_BAL = TDB5420_AWA_5420.MAX_BAL;
        TDCPRAM.MIN_RAT = TDB5420_AWA_5420.MIN_RAT;
        TDCPRAM.MAX_RAT = TDB5420_AWA_5420.MAX_RAT;
        TDCPRAM.ZX_FLG = TDB5420_AWA_5420.ZX_FLG;
        TDCPRAM.PCT_S = TDB5420_AWA_5420.PCT_S;
        TDCPRAM.FD_RAT = TDB5420_AWA_5420.FD_RAT;
        TDCPRAM.RUL_CD = TDB5420_AWA_5420.RUL_CD;
        TDCPRAM.HY_RAT = TDB5420_AWA_5420.HY_RAT;
        TDCPRAM.YX_TYP = TDB5420_AWA_5420.YX_TYP;
        TDCPRAM.ZQ_TYP = TDB5420_AWA_5420.ZQ_TYP;
        TDCPRAM.FX_TERM = TDB5420_AWA_5420.FX_TERM;
        TDCPRAM.ZR_TYP = TDB5420_AWA_5420.ZR_TYP;
        TDCPRAM.SH_TYP = TDB5420_AWA_5420.SH_TYP;
        TDCPRAM.ZY_TYP = TDB5420_AWA_5420.ZY_TYP;
        TDCPRAM.TQ_TYP = TDB5420_AWA_5420.TQ_TYP;
        TDCPRAM.TQ_RAT = TDB5420_AWA_5420.TQ_RAT;
        TDCPRAM.WF_NO = TDB5420_AWA_5420.WF_NO;
        TDCPRAM.YQ_TYP = TDB5420_AWA_5420.YQ_TYP;
        TDCPRAM.YQ_RAT = TDB5420_AWA_5420.YQ_RAT;
        TDCPRAM.SY_TYP = TDB5420_AWA_5420.SY_TYP;
        TDCPRAM.SY_RAT = TDB5420_AWA_5420.SY_RAT;
        TDCPRAM.CHNL_NO = TDB5420_AWA_5420.CHNL_NO;
        TDCPRAM.MD_TYP = TDB5420_AWA_5420.MD_TYP;
        TDCPRAM.MDQX_TYP = TDB5420_AWA_5420.MDQX_TYP;
        TDCPRAM.CL_FLG = TDB5420_AWA_5420.CL_FLG;
        TDCPRAM.CQ_TERM = TDB5420_AWA_5420.CQ_TERM;
        TDCPRAM.GRPS_NO = TDB5420_AWA_5420.GRPS_NO;
        TDCPRAM.HER_BAL = TDB5420_AWA_5420.HER_BAL;
        TDCPRAM.ADD_BAL = TDB5420_AWA_5420.ADD_BAL;
        TDCPRAM.PART_NUM = TDB5420_AWA_5420.PART_NUM;
        CEP.TRC(SCCGWA, TDB5420_AWA_5420.LST_CTL);
        CEP.TRC(SCCGWA, TDB5420_AWA_5420.RMK);
        TDCPRAM.PAGE_ROW = TDB5420_AWA_5420.PAGE_ROW;
        TDCPRAM.PAGE_NUM = TDB5420_AWA_5420.PAGE_NUM;
        TDCPRAM.LST_CTL = TDB5420_AWA_5420.LST_CTL;
        TDCPRAM.RMK = TDB5420_AWA_5420.RMK;
        if (TDB5420_AWA_5420.FUNC == 'I' 
            || TDB5420_AWA_5420.FUNC == 'O') {
            S000_CALL_TDZPRAM();
        } else {
            if (TDB5420_AWA_5420.FUNC == 'A') {
                TDCPRAM.FUNC = 'A';
            }
            if (TDB5420_AWA_5420.FUNC == 'M') {
                TDCPRAM.FUNC = 'M';
            }
            if (TDB5420_AWA_5420.FUNC == 'D') {
                TDCPRAM.FUNC = 'D';
            }
            CEP.TRC(SCCGWA, "1111");
            S000_CALL_TDZPRAMS();
            CEP.TRC(SCCGWA, "2222");
        }
    }
    public void S000_CALL_TDZPRAM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TDZPRAM", TDCPRAM);
    }
    public void S000_CALL_TDZPRAMS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "TD-TDZPRAMS", TDCPRAM);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
