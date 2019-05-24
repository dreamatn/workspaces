package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZICCYR {
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTACLNK_RD;
    DBParm DCTIACCY_RD;
    boolean pgmRtn = false;
    String WS_VIA_AC = " ";
    char WS_IACCY_REC_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRIACCY DCRIACCY = new DCRIACCY();
    DCRACLNK DCRACLNK = new DCRACLNK();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCICCYR DCCICCYR;
    public void MP(SCCGWA SCCGWA, DCCICCYR DCCICCYR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCICCYR = DCCICCYR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZICCYR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCICCYR.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHECK_CCY_PROC();
        if (pgmRtn) return;
        B030_GET_CCY_INFO_PROC();
        if (pgmRtn) return;
        B040_OUTPUT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111111111");
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCICCYR.INP_DATA.VIA_AC.trim().length() == 0 
            && DCCICCYR.INP_DATA.CARD_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT, DCCICCYR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYR.INP_DATA.VIA_AC.trim().length() > 0 
            && DCCICCYR.INP_DATA.CARD_NO.trim().length() > 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_VIA_MUST_INPUT, DCCICCYR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYR.INP_DATA.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_M_INPUT, DCCICCYR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYR.INP_DATA.CCY_TYPE != '1' 
            && DCCICCYR.INP_DATA.CCY_TYPE != '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID, DCCICCYR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICCYR.INP_DATA.CCY.equalsIgnoreCase("156") 
            && DCCICCYR.INP_DATA.CCY_TYPE == '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID, DCCICCYR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHECK_CCY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = DCCICCYR.INP_DATA.CCY;
        S000_CALL_BPZQCCY();
        if (pgmRtn) return;
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCICCYR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_CCY_INFO_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCICCYR.INP_DATA.VIA_AC);
        if (DCCICCYR.INP_DATA.CARD_NO.trim().length() > 0) {
            IBS.init(SCCGWA, DCRACLNK);
            DCRACLNK.KEY.CARD_NO = DCCICCYR.INP_DATA.CARD_NO;
            T00_READ_DCTACLNK();
            if (pgmRtn) return;
            WS_VIA_AC = DCRACLNK.VIA_AC;
        } else {
            WS_VIA_AC = DCCICCYR.INP_DATA.VIA_AC;
        }
        IBS.init(SCCGWA, DCRIACCY);
        DCRIACCY.KEY.VIA_AC = WS_VIA_AC;
        DCRIACCY.KEY.CCY = DCCICCYR.INP_DATA.CCY;
        DCRIACCY.KEY.CCY_TYPE = DCCICCYR.INP_DATA.CCY_TYPE;
        CEP.TRC(SCCGWA, WS_VIA_AC);
        CEP.TRC(SCCGWA, DCRIACCY.KEY.VIA_AC);
        CEP.TRC(SCCGWA, DCRIACCY.KEY.CCY);
        CEP.TRC(SCCGWA, DCRIACCY.KEY.CCY_TYPE);
        T00_READ_DCTIACCY();
        if (pgmRtn) return;
    }
    public void B040_OUTPUT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1");
        DCCICCYR.OUT_DATA.DD_TOT_BAL = DCRIACCY.DD_TOT_BAL;
        DCCICCYR.OUT_DATA.LAST_DD_BAL = DCRIACCY.LAST_DD_BAL;
        DCCICCYR.OUT_DATA.LAST_DD_ACDT = DCRIACCY.LAST_DD_ACDT;
        DCCICCYR.OUT_DATA.TD_TOT_BAL = DCRIACCY.TD_TOT_BAL;
        DCCICCYR.OUT_DATA.LAST_TD_BAL = DCRIACCY.LAST_TD_BAL;
        DCCICCYR.OUT_DATA.LAST_TD_ACDT = DCRIACCY.LAST_TD_ACDT;
        CEP.TRC(SCCGWA, "2");
        DCCICCYR.OUT_DATA.HLD_MTH = DCRIACCY.HLD_MTH;
        DCCICCYR.OUT_DATA.HLD_TOT_BAL = DCRIACCY.TOT_HLD_BAL;
        DCCICCYR.OUT_DATA.DD_HLD_BAL = DCRIACCY.DD_HLD_BAL;
        DCCICCYR.OUT_DATA.TD_HLD_BAL = DCRIACCY.TD_HLD_BAL;
        CEP.TRC(SCCGWA, "3");
        DCCICCYR.OUT_DATA.AMT1 = DCRIACCY.AMT1;
        DCCICCYR.OUT_DATA.AMT2 = DCRIACCY.AMT2;
        DCCICCYR.OUT_DATA.AMT3 = DCRIACCY.AMT3;
        DCCICCYR.OUT_DATA.AMT4 = DCRIACCY.AMT4;
        CEP.TRC(SCCGWA, "4");
        DCCICCYR.OUT_DATA.CCY_BAL = DCCICCYR.OUT_DATA.DD_TOT_BAL + DCCICCYR.OUT_DATA.TD_TOT_BAL;
        CEP.TRC(SCCGWA, "5");
        if (DCRIACCY.HLD_MTH == '1') {
            DCCICCYR.OUT_DATA.AVL_BAL = DCCICCYR.OUT_DATA.CCY_BAL - DCCICCYR.OUT_DATA.HLD_TOT_BAL;
        } else if (DCRIACCY.HLD_MTH == '2') {
            DCCICCYR.OUT_DATA.AVL_BAL = DCCICCYR.OUT_DATA.CCY_BAL - DCCICCYR.OUT_DATA.DD_HLD_BAL - DCCICCYR.OUT_DATA.TD_HLD_BAL;
        } else {
            DCCICCYR.OUT_DATA.AVL_BAL = DCCICCYR.OUT_DATA.CCY_BAL;
        }
        CEP.TRC(SCCGWA, "6");
        DCCICCYR.OUT_DATA.CRT_DATE = DCRIACCY.CRT_DATE;
        CEP.TRC(SCCGWA, DCRIACCY.CRT_TLR);
        DCCICCYR.OUT_DATA.CRT_TLR = DCRIACCY.CRT_TLR;
        CEP.TRC(SCCGWA, DCRIACCY.UPDTBL_DATE);
        DCCICCYR.OUT_DATA.UPDTBL_DATE = DCRIACCY.UPDTBL_DATE;
        CEP.TRC(SCCGWA, DCRIACCY.UPDTBL_TLR);
        DCCICCYR.OUT_DATA.UPDTBL_TLR = DCRIACCY.UPDTBL_TLR;
        CEP.TRC(SCCGWA, "2222222222222");
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
    }
    public void T00_READ_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        IBS.READ(SCCGWA, DCRACLNK, DCTACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_AC_CLOSED, DCCICCYR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTACLNK ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_DCTIACCY() throws IOException,SQLException,Exception {
        DCTIACCY_RD = new DBParm();
        DCTIACCY_RD.TableName = "DCTIACCY";
        IBS.READ(SCCGWA, DCRIACCY, DCTIACCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_CCY_RCD_NOT_FND, DCCICCYR.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTIACCY ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACCY";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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
        if (DCCICCYR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCICCYR=");
            CEP.TRC(SCCGWA, DCCICCYR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
