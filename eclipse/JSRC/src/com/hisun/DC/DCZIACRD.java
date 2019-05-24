package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZIACRD {
    brParm DCTIAACR_BR = new brParm();
    DBParm DCTIAACR_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_ACR_NUM = 0;
    int WS_IAACR_SEQ = 0;
    char WS_END_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRIAACR DCRIAACR = new DCRIAACR();
    String WS_IACRD_VIA_AC = " ";
    String WS_IACRD_SUB_AC = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCIACRD DCCIACRD;
    public void MP(SCCGWA SCCGWA, DCCIACRD DCCIACRD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCIACRD = DCCIACRD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZIACRD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCIACRD.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_DFT_AC_OPR_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCIACRD.VIA_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT, DCCIACRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCIACRD.SUB_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SUB_AC_MUST_INPUT, DCCIACRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCIACRD.CCY.equalsIgnoreCase("156") 
            && DCCIACRD.CCY_TYP == '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID, DCCIACRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCIACRD.OPR != '1' 
            && DCCIACRD.OPR != '2' 
            && DCCIACRD.OPR != '3') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_OPR_INVALID, DCCIACRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_DFT_AC_OPR_PROC() throws IOException,SQLException,Exception {
        if (DCCIACRD.OPR == '1') {
            R000_SET_DFT_AC_PROC();
            if (pgmRtn) return;
        } else if (DCCIACRD.OPR == '2') {
            R000_CAN_DFT_AC_PROC();
            if (pgmRtn) return;
        } else if (DCCIACRD.OPR == '3') {
            R000_CAN_RL_PROC();
            if (pgmRtn) return;
        } else {
        }
    }
    public void R000_SET_DFT_AC_PROC() throws IOException,SQLException,Exception {
        T000_STRBR_BY_VIA_AC_PROC();
        if (pgmRtn) return;
        T000_READ_NEXT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRIAACR.SUB_AC);
        CEP.TRC(SCCGWA, DCCIACRD.SUB_AC);
        while (WS_END_FLG != 'Y' 
            && DCRIAACR.SUB_AC.compareTo(DCCIACRD.SUB_AC) <= 0) {
            if (DCRIAACR.CCY.equalsIgnoreCase(DCCIACRD.CCY) 
                && DCRIAACR.CCY_TYPE == DCCIACRD.CCY_TYP) {
                if (DCRIAACR.ACCR_FLG == '0') {
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_AC_ALR_CAN_RL, DCCIACRD.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (DCRIAACR.DEFAULT_FLG == 'Y') {
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_DFT_AC, DCCIACRD.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                WS_ACR_NUM = WS_ACR_NUM + 1;
                WS_IAACR_SEQ = DCRIAACR.KEY.SEQ;
                R000_10_SET_DFT_AC_PROC();
                if (pgmRtn) return;
            }
            T000_READ_NEXT_PROC();
            if (pgmRtn) return;
        }
        T000_END_BROWSE_PROC();
        if (pgmRtn) return;
        if (WS_ACR_NUM == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ACR_RCD_NOT_FND, DCCIACRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_10_SET_DFT_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.KEY.VIA_AC = DCCIACRD.VIA_AC;
        DCRIAACR.KEY.SEQ = WS_IAACR_SEQ;
        T000_READ_DCTIAACR_R();
        if (pgmRtn) return;
        DCRIAACR.DEFAULT_FLG = 'Y';
        DCRIAACR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIAACR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTIAACR();
        if (pgmRtn) return;
    }
    public void R000_CAN_DFT_AC_PROC() throws IOException,SQLException,Exception {
        T000_STRBR_BY_VIA_AC_PROC();
        if (pgmRtn) return;
        T000_READ_NEXT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRIAACR.SUB_AC);
        CEP.TRC(SCCGWA, DCCIACRD.SUB_AC);
        while (WS_END_FLG != 'Y' 
            && DCRIAACR.SUB_AC.compareTo(DCCIACRD.SUB_AC) <= 0) {
            if (DCRIAACR.CCY.equalsIgnoreCase(DCCIACRD.CCY) 
                && DCRIAACR.CCY_TYPE == DCCIACRD.CCY_TYP) {
                if (DCRIAACR.ACCR_FLG == '0') {
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_AC_ALR_CAN_RL, DCCIACRD.RC);
                }
                if (DCRIAACR.DEFAULT_FLG == 'N') {
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_CAN_DFT_AC, DCCIACRD.RC);
                }
                WS_ACR_NUM = WS_ACR_NUM + 1;
                WS_IAACR_SEQ = DCRIAACR.KEY.SEQ;
                R000_10_CAN_DFT_AC_PROC();
                if (pgmRtn) return;
            }
            T000_READ_NEXT_PROC();
            if (pgmRtn) return;
        }
        T000_END_BROWSE_PROC();
        if (pgmRtn) return;
        if (WS_ACR_NUM == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ACR_RCD_NOT_FND, DCCIACRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_10_CAN_DFT_AC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.KEY.VIA_AC = DCCIACRD.VIA_AC;
        DCRIAACR.KEY.SEQ = WS_IAACR_SEQ;
        T000_READ_DCTIAACR_R();
        if (pgmRtn) return;
        DCRIAACR.DEFAULT_FLG = 'N';
        DCRIAACR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIAACR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTIAACR();
        if (pgmRtn) return;
    }
    public void R000_CAN_RL_PROC() throws IOException,SQLException,Exception {
        T000_STRBR_BY_VIA_AC_PROC();
        if (pgmRtn) return;
        T000_READ_NEXT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRIAACR.SUB_AC);
        CEP.TRC(SCCGWA, DCCIACRD.SUB_AC);
        while (WS_END_FLG != 'Y' 
            && DCRIAACR.SUB_AC.compareTo(DCCIACRD.SUB_AC) <= 0) {
            if (DCRIAACR.ACCR_FLG == '0') {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_AC_ALR_CAN_RL, DCCIACRD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            WS_ACR_NUM = WS_ACR_NUM + 1;
            WS_IAACR_SEQ = DCRIAACR.KEY.SEQ;
            R000_10_CAN_RL_PROC();
            if (pgmRtn) return;
            T000_READ_NEXT_PROC();
            if (pgmRtn) return;
        }
        T000_END_BROWSE_PROC();
        if (pgmRtn) return;
        if (WS_ACR_NUM == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ACR_RCD_NOT_FND, DCCIACRD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_10_CAN_RL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.KEY.VIA_AC = DCCIACRD.VIA_AC;
        DCRIAACR.KEY.SEQ = WS_IAACR_SEQ;
        T000_READ_DCTIAACR_R();
        if (pgmRtn) return;
        DCRIAACR.ACCR_FLG = '0';
        if (DCRIAACR.DEFAULT_FLG == 'Y') {
            DCRIAACR.DEFAULT_FLG = 'N';
        }
        DCRIAACR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIAACR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTIAACR();
        if (pgmRtn) return;
    }
    public void T000_STRBR_BY_VIA_AC_PROC() throws IOException,SQLException,Exception {
        WS_IACRD_VIA_AC = DCCIACRD.VIA_AC;
        WS_IACRD_SUB_AC = DCCIACRD.SUB_AC;
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "VIA_AC = :WS_IACRD_VIA_AC "
            + "AND SUB_AC >= :WS_IACRD_SUB_AC";
        DCTIAACR_BR.rp.order = "SUB_AC";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_END_FLG = 'Y';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
    }
    public void T000_READ_DCTIAACR_R() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.upd = true;
        IBS.READ(SCCGWA, DCRIAACR, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ACR_RCD_NOT_FND, DCCIACRD.RC);
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTIAACR ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DCRIAACR, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "UPDATE TABLE DCTIAACR ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
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
        if (DCCIACRD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCIACRD=");
            CEP.TRC(SCCGWA, DCCIACRD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
