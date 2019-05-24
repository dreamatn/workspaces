package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZILNKU {
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
    DCCILNKU DCCILNKU;
    public void MP(SCCGWA SCCGWA, DCCILNKU DCCILNKU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCILNKU = DCCILNKU;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZILNKU return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCILNKU.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B040_UPD_LNK_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCILNKU.KEY.CARD_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_NO_MUST_INPUT, DCCILNKU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCILNKU.KEY.VIA_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT, DCCILNKU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCILNKU.CARD_AC_STATUS != '0' 
            && DCCILNKU.CARD_AC_STATUS != '1') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_AC_STS_INVALID, DCCILNKU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_RTV_LNK_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRACLNK);
        DCRACLNK.KEY.CARD_NO = DCCILNKU.KEY.CARD_NO;
        DCRACLNK.VIA_AC = DCCILNKU.KEY.VIA_AC;
        T000_READUPD_DCTACLNK();
        if (pgmRtn) return;
    }
    public void B030_CHK_LNK_INFO() throws IOException,SQLException,Exception {
        if (DCCILNKU.CARD_AC_STATUS == '1') {
            if (WS_RETURN_INFO == 'F' 
                && DCRACLNK.CARD_AC_STATUS == '1') {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_AC_LNK_EXIST, DCCILNKU.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (DCCILNKU.CARD_AC_STATUS == '0') {
            if (WS_RETURN_INFO == 'N' 
                || DCRACLNK.CARD_AC_STATUS == '0') {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_AC_LNK_NOT_FND, DCCILNKU.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
        }
    }
    public void B040_UPD_LNK_PROC() throws IOException,SQLException,Exception {
        DCRACLNK.KEY.CARD_NO = DCCILNKU.KEY.CARD_NO;
        DCRACLNK.VIA_AC = DCCILNKU.KEY.VIA_AC;
        if (DCCILNKU.CARD_AC_STATUS == '1') {
            R000_TRANS_DATA_PROC();
            if (pgmRtn) return;
            T000_WRITE_DCTACLNK();
            if (pgmRtn) return;
        } else {
            T000_READUPD_DCTACLNK();
            if (pgmRtn) return;
            R000_TRANS_DATA_PROC();
            if (pgmRtn) return;
            DCRACLNK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTACLNK();
            if (pgmRtn) return;
        }
    }
    public void R000_TRANS_DATA_PROC() throws IOException,SQLException,Exception {
        DCRACLNK.CARD_AC_STATUS = DCCILNKU.CARD_AC_STATUS;
        DCRACLNK.CARD_TYPE = DCCILNKU.CARD_TYPE;
        DCRACLNK.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRACLNK.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRACLNK.UPDTBL_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DCRACLNK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void T000_READUPD_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        DCTACLNK_RD.upd = true;
        IBS.READ(SCCGWA, DCRACLNK, DCTACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RETURN_INFO = 'N';
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_AC_LNK_NOT_FND, DCCILNKU.RC);
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
    public void T000_REWRITE_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        DCTACLNK_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DCRACLNK, DCTACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "UPDATE TABLE DCTACLNK ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTACLNK";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTACLNK() throws IOException,SQLException,Exception {
        DCTACLNK_RD = new DBParm();
        DCTACLNK_RD.TableName = "DCTACLNK";
        DCTACLNK_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRACLNK, DCTACLNK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "WRITE TABLE DCTACLNK ERROR!";
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
        if (DCCILNKU.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCILNKU=");
            CEP.TRC(SCCGWA, DCCILNKU);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
