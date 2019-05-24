package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZILNKR {
    DBParm DCTACLNK_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char WS_RETURN_INFO = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRACLNK DCRACLNK = new DCRACLNK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCILNKR DCCILNKR;
    public void MP(SCCGWA SCCGWA, DCCILNKR DCCILNKR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCILNKR = DCCILNKR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZILNKR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCILNKR.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_LNK_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCILNKR.KEY.CARD_NO.trim().length() == 0 
            && DCCILNKR.KEY.VIA_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_VIA_MUST_INPUT, DCCILNKR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQ_LNK_DATA() throws IOException,SQLException,Exception {
        T000_READ_DCTACLNK();
        if (pgmRtn) return;
        DCCILNKR.KEY.CARD_NO = DCRACLNK.KEY.CARD_NO;
        DCCILNKR.KEY.VIA_AC = DCRACLNK.VIA_AC;
        DCCILNKR.CARD_AC_STATUS = DCRACLNK.CARD_AC_STATUS;
        DCCILNKR.CRT_DATE = DCRACLNK.CRT_DATE;
        DCCILNKR.CRT_TLR = DCRACLNK.CRT_TLR;
        DCCILNKR.UPDTBL_DATE = DCRACLNK.UPDTBL_DATE;
        DCCILNKR.UPDTBL_TLR = DCRACLNK.UPDTBL_TLR;
        DCCILNKR.TS = DCRACLNK.TS;
    }
    public void T000_READ_DCTACLNK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRACLNK);
        DCRACLNK.KEY.CARD_NO = DCCILNKR.KEY.CARD_NO;
        DCRACLNK.VIA_AC = DCCILNKR.KEY.VIA_AC;
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        IBS.READ(SCCGWA, DCRACLNK, DCTACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_AC_RCD_NOT_FND, DCCILNKR.RC);
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
        if (DCCILNKR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCILNKR=");
            CEP.TRC(SCCGWA, DCCILNKR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
