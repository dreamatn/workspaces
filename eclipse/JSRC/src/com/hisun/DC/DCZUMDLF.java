package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZUMDLF {
    DBParm DCTPFDE_RD;
    brParm DCTPFDE_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC995";
    short WS_CNT = 0;
    String WS_ERR_MSG = " ";
    int WS_I_CNT = 0;
    int WS_P_ROW = 0;
    int WS_L_ROW = 0;
    int WS_STR_NUM = 0;
    int WS_END_NUM = 0;
    DCZUMDLF_WS_OUTPUT_FMT WS_OUTPUT_FMT = new DCZUMDLF_WS_OUTPUT_FMT();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRPFDE DCRPFDE = new DCRPFDE();
    int WS_L_CNT = 0;
    SCCGWA SCCGWA;
    DCCUMDLF DCCUMDLF;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    public void MP(SCCGWA SCCGWA, DCCUMDLF DCCUMDLF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCUMDLF = DCCUMDLF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZUMDLF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DCCUMDLF.IO_AREA.FUNC == 'Q') {
            B100_PLAN_BRO();
            if (pgmRtn) return;
        } else if (DCCUMDLF.IO_AREA.FUNC == 'A') {
            B500_PLAN_ADD();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_FUNC_ERR, DCCUMDLF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B100_PLAN_BRO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUMDLF.IO_AREA.AGT_NO);
        CEP.TRC(SCCGWA, DCCUMDLF.IO_AREA.LN_AC);
        if (DCCUMDLF.IO_AREA.AGT_NO.trim().length() == 0 
            && DCCUMDLF.IO_AREA.LN_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT, DCCUMDLF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRPFDE);
        DCRPFDE.KEY.AGT_NO = DCCUMDLF.IO_AREA.AGT_NO;
        DCRPFDE.LN_AC = DCCUMDLF.IO_AREA.LN_AC;
        T000_GROUP_DCTPFDE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_L_CNT);
        if (DCCUMDLF.I_AREA.PAGE_ROW == 0 
            || DCCUMDLF.I_AREA.PAGE_ROW > 10) {
            WS_P_ROW = 10;
        } else {
            WS_P_ROW = DCCUMDLF.I_AREA.PAGE_ROW;
        }
        if (WS_L_CNT > 0) {
            WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_NUM = WS_L_CNT;
            WS_L_ROW = WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_NUM % WS_P_ROW;
            WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE = (int) ((WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_NUM - WS_L_ROW) / WS_P_ROW);
            if (WS_L_ROW == 0) {
                WS_L_ROW = WS_P_ROW;
            } else {
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE += 1;
            }
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_L_ROW);
            CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE);
            CEP.TRC(SCCGWA, DCCUMDLF.I_AREA.PAGE_NUM);
            if (DCCUMDLF.I_AREA.PAGE_NUM == 0) {
                DCCUMDLF.I_AREA.PAGE_NUM += 1;
                CEP.TRC(SCCGWA, DCCUMDLF.I_AREA.PAGE_NUM);
            }
            if (DCCUMDLF.I_AREA.PAGE_NUM >= WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE) {
                CEP.TRC(SCCGWA, ">>>===");
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_CURR_PAGE = WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE;
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_PAGE_ROW = WS_L_ROW;
            } else {
                CEP.TRC(SCCGWA, "<<<<<<");
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_CURR_PAGE = DCCUMDLF.I_AREA.PAGE_NUM;
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'N';
                WS_OUTPUT_FMT.WS_PAGE_INF.WS_PAGE_ROW = WS_P_ROW;
            }
            WS_STR_NUM = ( WS_OUTPUT_FMT.WS_PAGE_INF.WS_CURR_PAGE - 1 ) * WS_P_ROW;
            WS_END_NUM = WS_OUTPUT_FMT.WS_PAGE_INF.WS_CURR_PAGE * WS_P_ROW;
            CEP.TRC(SCCGWA, "PAGE1 INFO:");
            CEP.TRC(SCCGWA, WS_L_CNT);
            CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_NUM);
            CEP.TRC(SCCGWA, WS_P_ROW);
            CEP.TRC(SCCGWA, WS_L_ROW);
            CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE);
            CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_PAGE_INF.WS_PAGE_ROW);
            CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_PAGE_INF.WS_CURR_PAGE);
            CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_PAGE_INF.WS_LAST_PAGE);
            CEP.TRC(SCCGWA, WS_STR_NUM);
            CEP.TRC(SCCGWA, WS_END_NUM);
            T000_STARTBR_DCTPFDE();
            if (pgmRtn) return;
            T000_READNEXT_DCTPFDE();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
                && WS_I_CNT <= WS_END_NUM) {
                if (WS_I_CNT > WS_STR_NUM 
                    && WS_I_CNT <= WS_END_NUM) {
                    WS_CNT += 1;
                    IBS.init(SCCGWA, WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1]);
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_AGT_NO = DCRPFDE.KEY.AGT_NO;
                    CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_AGT_NO);
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_TR_DATE = DCRPFDE.KEY.TR_DATE;
                    CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_TR_DATE);
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_JRN_NO = DCRPFDE.KEY.JRN_NO;
                    CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_JRN_NO);
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_AGT_TYP = DCRPFDE.AGT_TYP;
                    CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_AGT_TYP);
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_LN_AC = DCRPFDE.LN_AC;
                    CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_LN_AC);
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_DD_AC = DCRPFDE.DD_AC;
                    CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_DD_AC);
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_LN_INT_AMT = DCRPFDE.LN_INT_AMT;
                    CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_LN_INT_AMT);
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_INC_AMT = DCRPFDE.INC_AMT;
                    CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_INC_AMT);
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_DED_AMT = DCRPFDE.DED_AMT;
                    CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_DED_AMT);
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_DED_PER = DCRPFDE.DED_PER;
                    CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_DED_PER);
                    WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_DED_DATE = DCRPFDE.DED_DATE;
                    CEP.TRC(SCCGWA, WS_OUTPUT_FMT.WS_OUT_DATA[WS_CNT-1].WS_DED_DATE);
                }
                T000_READNEXT_DCTPFDE();
                if (pgmRtn) return;
            }
            T000_ENDBR_DCTPFDE();
            if (pgmRtn) return;
        } else {
            WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_NUM = 0;
            WS_OUTPUT_FMT.WS_PAGE_INF.WS_TOTAL_PAGE = 0;
            WS_OUTPUT_FMT.WS_PAGE_INF.WS_CURR_PAGE = 0;
            WS_OUTPUT_FMT.WS_PAGE_INF.WS_LAST_PAGE = 'Y';
            WS_OUTPUT_FMT.WS_PAGE_INF.WS_PAGE_ROW = 0;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT_FMT;
        SCCFMT.DATA_LEN = 1992;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B500_PLAN_ADD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCUMDLF.IO_AREA.AGT_NO);
        if (DCCUMDLF.IO_AREA.AGT_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT, DCCUMDLF.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRPFDE);
        DCRPFDE.KEY.AGT_NO = DCCUMDLF.IO_AREA.AGT_NO;
        if (DCCUMDLF.IO_AREA.TXN_DT == 0) {
            DCRPFDE.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            DCRPFDE.KEY.TR_DATE = DCCUMDLF.IO_AREA.TXN_DT;
        }
        if (DCCUMDLF.IO_AREA.TXN_JRNNO == 0) {
            DCRPFDE.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        } else {
            DCRPFDE.KEY.JRN_NO = DCCUMDLF.IO_AREA.TXN_JRNNO;
        }
        DCRPFDE.AGT_TYP = DCCUMDLF.IO_AREA.AGT_TYP;
        DCRPFDE.LN_AC = DCCUMDLF.IO_AREA.LN_AC;
        DCRPFDE.DD_AC = DCCUMDLF.IO_AREA.DD_AC;
        DCRPFDE.LN_INT_AMT = DCCUMDLF.IO_AREA.LN_INT_AMT;
        DCRPFDE.INC_AMT = DCCUMDLF.IO_AREA.INC_AMT;
        DCRPFDE.DED_AMT = DCCUMDLF.IO_AREA.DED_AMT;
        DCRPFDE.DED_PER = DCCUMDLF.IO_AREA.DED_PER;
        DCRPFDE.DED_DATE = DCCUMDLF.IO_AREA.DED_DT;
        DCRPFDE.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRPFDE.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTPFDE();
        if (pgmRtn) return;
    }
    public void T000_GROUP_DCTPFDE() throws IOException,SQLException,Exception {
        DCTPFDE_RD = new DBParm();
        DCTPFDE_RD.TableName = "DCTPFDE";
        DCTPFDE_RD.set = "WS-L-CNT=COUNT(*)";
        DCTPFDE_RD.where = "( AGT_NO = :DCRPFDE.KEY.AGT_NO "
            + "OR :DCRPFDE.KEY.AGT_NO = ' ' ) "
            + "AND ( LN_AC = :DCRPFDE.LN_AC "
            + "OR :DCRPFDE.LN_AC = ' ' )";
        IBS.GROUP(SCCGWA, DCRPFDE, this, DCTPFDE_RD);
    }
    public void T000_STARTBR_DCTPFDE() throws IOException,SQLException,Exception {
        DCTPFDE_BR.rp = new DBParm();
        DCTPFDE_BR.rp.TableName = "DCTPFDE";
        DCTPFDE_BR.rp.where = "( AGT_NO = :DCRPFDE.KEY.AGT_NO "
            + "OR :DCRPFDE.KEY.AGT_NO = ' ' ) "
            + "AND ( LN_AC = :DCRPFDE.LN_AC "
            + "OR :DCRPFDE.LN_AC = ' ' )";
        IBS.STARTBR(SCCGWA, DCRPFDE, this, DCTPFDE_BR);
    }
    public void T000_READNEXT_DCTPFDE() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRPFDE, this, DCTPFDE_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_I_CNT += 1;
        }
    }
    public void T000_ENDBR_DCTPFDE() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTPFDE_BR);
    }
    public void T000_WRITE_DCTPFDE() throws IOException,SQLException,Exception {
        DCTPFDE_RD = new DBParm();
        DCTPFDE_RD.TableName = "DCTPFDE";
        IBS.WRITE(SCCGWA, DCRPFDE, DCTPFDE_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DUPKEY, DCCUMDLF.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTPFDE";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
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
        if (DCCUMDLF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCUMDLF=");
            CEP.TRC(SCCGWA, DCCUMDLF);
        }
    } //FROM #ENDIF
        CEP.TRC(SCCGWA, "BEFORE-Z-RET");
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
