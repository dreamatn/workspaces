package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZIACRA {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    brParm DCTIAACR_BR = new brParm();
    DBParm DCTIAACR_RD;
    boolean pgmRtn = false;
    int WS_ACR_NUM = 0;
    int WS_SEQ_NUM = 0;
    char WS_RL_FLAG = ' ';
    char WS_END_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRIAACR DCRIAACR = new DCRIAACR();
    BPCQCCY BPCQCCY = new BPCQCCY();
    String WS_IACRA_VIA_AC = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCIACRA DCCIACRA;
    public void MP(SCCGWA SCCGWA, DCCIACRA DCCIACRA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCIACRA = DCCIACRA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZIACRA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCIACRA.RC);
        CEP.TRC(SCCGWA, DCCIACRA.INP_DATA.VCH_TYPE);
        CEP.TRC(SCCGWA, DCCIACRA.INP_DATA.VCH_NO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHECK_CCY_PROC();
        if (pgmRtn) return;
        B030_CHECK_ACR_PROC();
        if (pgmRtn) return;
        B040_CRT_UPD_ACR_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCIACRA.INP_DATA.VIA_AC);
        if (DCCIACRA.INP_DATA.VIA_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT, DCCIACRA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCIACRA.INP_DATA.FRM_APP);
        if (DCCIACRA.INP_DATA.FRM_APP.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_APP_MUST_INPUT, DCCIACRA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCIACRA.INP_DATA.SUB_AC);
        if (DCCIACRA.INP_DATA.SUB_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_SUB_AC_MUST_INPUT, DCCIACRA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCIACRA.INP_DATA.DEFAULT_FLG);
        if (DCCIACRA.INP_DATA.DEFAULT_FLG != ' ' 
            && DCCIACRA.INP_DATA.DEFAULT_FLG != 'Y' 
            && DCCIACRA.INP_DATA.DEFAULT_FLG != 'N') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_DEFAULT_FLG_INVALID, DCCIACRA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCIACRA.INP_DATA.CCY_TYPE);
        if (DCCIACRA.INP_DATA.CCY_TYPE != ' ' 
            && DCCIACRA.INP_DATA.CCY_TYPE != '1' 
            && DCCIACRA.INP_DATA.CCY_TYPE != '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID, DCCIACRA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCIACRA.INP_DATA.CCY);
        if (DCCIACRA.INP_DATA.CCY.equalsIgnoreCase("156") 
            && DCCIACRA.INP_DATA.CCY_TYPE == '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID, DCCIACRA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCCIACRA.INP_DATA.VCH_TYPE);
    }
    public void B020_CHECK_CCY_PROC() throws IOException,SQLException,Exception {
        if (DCCIACRA.INP_DATA.CCY.trim().length() > 0) {
            IBS.init(SCCGWA, BPCQCCY);
            BPCQCCY.DATA.CCY = DCCIACRA.INP_DATA.CCY;
            S000_CALL_BPZQCCY();
            if (pgmRtn) return;
            if (BPCQCCY.RC.RTNCODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCIACRA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_CHECK_ACR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.KEY.VIA_AC = DCCIACRA.INP_DATA.VIA_AC;
        T000_STRBR_BY_VIA_AC_PROC();
        if (pgmRtn) return;
        T000_READ_NEXT_PROC();
        if (pgmRtn) return;
        while (WS_END_FLG != 'Y') {
            WS_SEQ_NUM = DCRIAACR.KEY.SEQ;
            CEP.TRC(SCCGWA, DCRIAACR.KEY.SEQ);
            CEP.TRC(SCCGWA, WS_SEQ_NUM);
            WS_ACR_NUM = WS_ACR_NUM + 1;
            if (DCCIACRA.INP_DATA.SUB_AC.equalsIgnoreCase(DCRIAACR.SUB_AC)) {
                if (DCRIAACR.ACCR_FLG == '1') {
                    IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ACR_ALR_EXS, DCCIACRA.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (DCRIAACR.ACCR_FLG == '0') {
                    WS_RL_FLAG = 'Y';
                    WS_END_FLG = 'Y';
                    R000_UPD_RL_PROC();
                    if (pgmRtn) return;
                }
            }
            T000_READ_NEXT_PROC();
            if (pgmRtn) return;
        }
        T000_END_BROWSE_PROC();
        if (pgmRtn) return;
        if (WS_ACR_NUM >= 999999) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_RL_NUM_GT_MAX_NUM, DCCIACRA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_CRT_UPD_ACR_PROC() throws IOException,SQLException,Exception {
        if (WS_ACR_NUM == 0 
            || WS_RL_FLAG != 'Y') {
            R000_CRT_ACR_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CRT_ACR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.KEY.VIA_AC = DCCIACRA.INP_DATA.VIA_AC;
        CEP.TRC(SCCGWA, WS_SEQ_NUM);
        DCRIAACR.KEY.SEQ = WS_SEQ_NUM + 1;
        CEP.TRC(SCCGWA, DCRIAACR.KEY.SEQ);
        DCCIACRA.OUT_DATA.SEQ = DCRIAACR.KEY.SEQ;
        CEP.TRC(SCCGWA, DCRIAACR.KEY.SEQ);
        DCRIAACR.FRM_APP = DCCIACRA.INP_DATA.FRM_APP;
        DCRIAACR.STD_AC_FLG = DCCIACRA.INP_DATA.STD_AC_FLG;
        DCRIAACR.SUB_AC = DCCIACRA.INP_DATA.SUB_AC;
        DCRIAACR.CCY = DCCIACRA.INP_DATA.CCY;
        DCRIAACR.CCY_TYPE = DCCIACRA.INP_DATA.CCY_TYPE;
        DCRIAACR.DEFAULT_FLG = DCCIACRA.INP_DATA.DEFAULT_FLG;
        DCRIAACR.VCH_TYPE = DCCIACRA.INP_DATA.VCH_TYPE;
        DCRIAACR.VCH_ID = DCCIACRA.INP_DATA.VCH_ID;
        if (DCCIACRA.INP_DATA.FRM_APP.equalsIgnoreCase("TD") 
            && DCCIACRA.INP_DATA.VCH_TYPE == '8' 
            && DCCIACRA.INP_DATA.VCH_NO.trim().length() == 0) {
            DCRIAACR.VCH_NO = "" + DCRIAACR.KEY.SEQ;
            JIBS_tmp_int = DCRIAACR.VCH_NO.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) DCRIAACR.VCH_NO = "0" + DCRIAACR.VCH_NO;
        } else {
            DCRIAACR.VCH_NO = DCCIACRA.INP_DATA.VCH_NO;
        }
        DCRIAACR.ACCR_FLG = '1';
        DCRIAACR.USAGE = DCCIACRA.INP_DATA.USAGE;
        DCRIAACR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIAACR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTIAACR();
        if (pgmRtn) return;
    }
    public void R000_UPD_RL_PROC() throws IOException,SQLException,Exception {
        DCRIAACR.ACCR_FLG = '1';
        if (DCCIACRA.INP_DATA.CCY.trim().length() > 0) {
            DCRIAACR.CCY = DCCIACRA.INP_DATA.CCY;
        }
        if (DCCIACRA.INP_DATA.CCY_TYPE != ' ') {
            DCRIAACR.CCY_TYPE = DCCIACRA.INP_DATA.CCY_TYPE;
        }
        if (DCCIACRA.INP_DATA.DEFAULT_FLG != ' ') {
            DCRIAACR.DEFAULT_FLG = DCCIACRA.INP_DATA.DEFAULT_FLG;
        }
        if (DCCIACRA.INP_DATA.VCH_TYPE != ' ') {
            DCRIAACR.VCH_TYPE = DCCIACRA.INP_DATA.VCH_TYPE;
        }
        if (DCCIACRA.INP_DATA.VCH_ID.trim().length() > 0) {
            DCRIAACR.VCH_ID = DCCIACRA.INP_DATA.VCH_ID;
        }
        if (DCCIACRA.INP_DATA.VCH_NO.trim().length() > 0) {
            DCRIAACR.VCH_NO = DCCIACRA.INP_DATA.VCH_NO;
        }
        DCRIAACR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIAACR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTIAACR();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZQCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-INQUIRE-CCY", BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCIACRA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STRBR_BY_VIA_AC_PROC() throws IOException,SQLException,Exception {
        WS_IACRA_VIA_AC = DCCIACRA.INP_DATA.VIA_AC;
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.upd = true;
        DCTIAACR_BR.rp.where = "VIA_AC = :WS_IACRA_VIA_AC";
        DCTIAACR_BR.rp.order = "SEQ";
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
    public void T000_WRITE_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRIAACR, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_ACR_RCD_ALR_EXS, DCCIACRA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
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
        if (DCCIACRA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCIACRA=");
            CEP.TRC(SCCGWA, DCCIACRA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
