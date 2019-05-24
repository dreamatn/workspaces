package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZIMSTT {
    DBParm DCTIAMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRIAMST DCRIAMST = new DCRIAMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCIMSTT DCCIMSTT;
    public void MP(SCCGWA SCCGWA, DCCIMSTT DCCIMSTT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCIMSTT = DCCIMSTT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZIMSTT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCIMSTT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_RTV_MST_DATA();
        if (pgmRtn) return;
        B030_CHK_MST_INFO();
        if (pgmRtn) return;
        B040_UPD_MST_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCIMSTT.VIA_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT, DCCIMSTT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCIMSTT.PRT_DTL_FLG != ' ') {
            if (DCCIMSTT.PRT_DTL_FLG != 'Y' 
                && DCCIMSTT.PRT_DTL_FLG != 'N') {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_DTL_FLG_INVALID, DCCIMSTT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_RTV_MST_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAMST);
        DCRIAMST.KEY.VIA_AC = DCCIMSTT.VIA_AC;
        T000_READ_DCTIAMST_R();
        if (pgmRtn) return;
    }
    public void B030_CHK_MST_INFO() throws IOException,SQLException,Exception {
        if (DCRIAMST.AC_STS == 'C') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_MST_CLOSE, DCCIMSTT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_UPD_MST_PROC() throws IOException,SQLException,Exception {
        DCRIAMST.PRT_DTL_FLG = DCCIMSTT.PRT_DTL_FLG;
        DCRIAMST.CURR_POS = DCCIMSTT.CURR_POS;
        DCRIAMST.VAL_NUM = DCCIMSTT.VAL_NUM;
        DCRIAMST.PROC_NUM = DCCIMSTT.PROC_NUM;
        DCRIAMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIAMST.UPDTBL_TLR = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        T000_REWRITE_DCTIAMST();
        if (pgmRtn) return;
    }
    public void T000_READ_DCTIAMST_R() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        DCTIAMST_RD.upd = true;
        IBS.READ(SCCGWA, DCRIAMST, DCTIAMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_MST_RCD_NOT_FND, DCCIMSTT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTIAMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTIAMST() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        DCTIAMST_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DCRIAMST, DCTIAMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "UPDATE TABLE DCTIAMST ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAMST";
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
        if (DCCIMSTT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCIMSTT=");
            CEP.TRC(SCCGWA, DCCIMSTT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
