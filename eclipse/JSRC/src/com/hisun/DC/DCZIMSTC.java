package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZIMSTC {
    DBParm DCTIAMST_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CICMACR CICMACR = new CICMACR();
    DCRIAMST DCRIAMST = new DCRIAMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCIMSTC DCCIMSTC;
    public void MP(SCCGWA SCCGWA, DCCIMSTC DCCIMSTC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCIMSTC = DCCIMSTC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZIMSTC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCIMSTC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_RTV_MST_DATA();
        if (pgmRtn) return;
        B030_CHK_MST_INFO();
        if (pgmRtn) return;
        B040_CLS_MST_PROC();
        if (pgmRtn) return;
        B050_CUS_RLT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCIMSTC.VIA_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT, DCCIMSTC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_RTV_MST_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAMST);
        DCRIAMST.KEY.VIA_AC = DCCIMSTC.VIA_AC;
        T000_READ_DCTIAMST_R();
        if (pgmRtn) return;
    }
    public void B030_CHK_MST_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCIMSTC.VIA_AC);
        if (DCRIAMST.AC_STS == 'C') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_MST_ALR_CLS, DCCIMSTC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_CLS_MST_PROC() throws IOException,SQLException,Exception {
        DCRIAMST.AC_STS = 'C';
        DCRIAMST.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIAMST.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRIAMST.CLOSE_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRIAMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIAMST.UPDTBL_TLR = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        T000_REWRITE_DCTIAMST();
        if (pgmRtn) return;
    }
    public void B050_CUS_RLT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICMACR);
        CICMACR.FUNC = '2';
        CEP.TRC(SCCGWA, CICMACR.CTLW1);
        if (DCRIAMST.VIA_FLG == '1') {
            CICMACR.DATA.ENTY_TYP = '3';
        } else {
            CICMACR.DATA.ENTY_TYP = '5';
        }
        CICMACR.DATA.AGR_NO = DCCIMSTC.VIA_AC;
        S000_CALL_CIZMACR();
        if (pgmRtn) return;
    }
    public void T000_READ_DCTIAMST_R() throws IOException,SQLException,Exception {
        DCTIAMST_RD = new DBParm();
        DCTIAMST_RD.TableName = "DCTIAMST";
        DCTIAMST_RD.upd = true;
        IBS.READ(SCCGWA, DCRIAMST, DCTIAMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_MST_RCD_NOT_FND, DCCIMSTC.RC);
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
    public void S000_CALL_CIZMACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-ACR", CICMACR);
        CEP.TRC(SCCGWA, CICMACR.RC.RC_CODE);
        if (CICMACR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICMACR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCIMSTC.RC);
            Z_RET();
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
        if (DCCIMSTC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCIMSTC=");
            CEP.TRC(SCCGWA, DCCIMSTC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
