package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSBRAR {
    String JIBS_tmp_str[] = new String[10];
    brParm DCTBRARC_BR = new brParm();
    DBParm DCTBRARC_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_9 = "DC055";
    int K_MAX_COL = 20;
    int K_MAX_ROW = 20;
    int K_COL_CNT = 18;
    String K_HIS_REMARK = "BR AND AREA NO MAINTAIN";
    String K_HIS_COPYBOOK = "DCRBRARC";
    String K_TBL_BRARC = "DCTBRARC";
    String K_PRDT_INF_MAINT = "DC-S-MAINT-AREA-NO";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    DCZSBRAR_WS_OUTPUT_VAL WS_OUTPUT_VAL = new DCZSBRAR_WS_OUTPUT_VAL();
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCRBRARC DCRBRARC = new DCRBRARC();
    DCRBRARC DCRBRARO = new DCRBRARC();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCSMPRD BPCSMPRD = new BPCSMPRD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    int WS_BR_NO_LOW = 0;
    int WS_BR_NO_HIGH = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSBRAR DCCSBRAR;
    public void MP(SCCGWA SCCGWA, DCCSBRAR DCCSBRAR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSBRAR = DCCSBRAR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSBRAR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, DCRBRARC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSBRAR);
        CEP.TRC(SCCGWA, DCCSBRAR.FUNC);
        if (DCCSBRAR.FUNC == 'A') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSBRAR.FUNC == 'U') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSBRAR.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (DCCSBRAR.FUNC == 'B') {
            B050_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.FUNC_FALSE, 1);
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSBRAR.VAL.KEY.BR_NO);
        CEP.TRC(SCCGWA, DCCSBRAR.VAL.AREA_NO);
        if (DCCSBRAR.VAL.KEY.BR_NO == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_BR_NO_M_INPUT);
        }
        if (DCCSBRAR.VAL.AREA_NO == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_AREA_NO_M_INPUT);
        }
        BPCPQORG.BR = DCCSBRAR.VAL.KEY.BR_NO;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBRARC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DCCSBRAR.VAL.KEY);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCRBRARC.KEY);
        T000_READ_DCTBRARC();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBRARC);
        CEP.TRC(SCCGWA, DCCSBRAR.VAL.KEY.BR_NO);
        DCRBRARC.KEY.BR_NO = DCCSBRAR.VAL.KEY.BR_NO;
        CEP.TRC(SCCGWA, DCCSBRAR.VAL.AREA_NO);
        DCRBRARC.AREA_NO = DCCSBRAR.VAL.AREA_NO;
        CEP.TRC(SCCGWA, DCCSBRAR.VAL.AREA_NM);
        DCRBRARC.AREA_NAME = DCCSBRAR.VAL.AREA_NM;
        DCRBRARC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRBRARC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRBRARC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRBRARC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTBRARC();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'D') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DUPKEY);
        }
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBRARC);
        DCRBRARC.KEY.BR_NO = DCCSBRAR.VAL.KEY.BR_NO;
        T000_READ_DCTBRARC_UPD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
        IBS.CLONE(SCCGWA, DCRBRARC, DCRBRARO);
        DCRBRARC.KEY.BR_NO = DCCSBRAR.VAL.KEY.BR_NO;
        DCRBRARC.AREA_NO = DCCSBRAR.VAL.AREA_NO;
        DCRBRARC.AREA_NAME = DCCSBRAR.VAL.AREA_NM;
        DCRBRARC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRBRARC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTBRARC();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'D') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DUPKEY);
        }
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBRARC);
        DCRBRARC.KEY.BR_NO = DCCSBRAR.VAL.KEY.BR_NO;
        T000_READ_DCTBRARC_UPD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.NOT_FOUND);
        }
        IBS.CLONE(SCCGWA, DCRBRARC, DCRBRARO);
        T000_DELETE_DCTBRARC();
        if (pgmRtn) return;
    }
    public void B050_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRBRARC);
        if (DCCSBRAR.VAL.KEY.BR_NO == 0) {
            WS_BR_NO_LOW = 0;
            WS_BR_NO_HIGH = 999999999;
        } else {
            WS_BR_NO_LOW = DCCSBRAR.VAL.KEY.BR_NO;
            WS_BR_NO_HIGH = DCCSBRAR.VAL.KEY.BR_NO;
        }
        T000_STARTBR_DCTBRARC();
        if (pgmRtn) return;
        T000_READNEXT_DCTBRARC();
        if (pgmRtn) return;
        B050_01_01_OUT_TITLE();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (WS_TBL_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B050_01_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            WS_CNT += 1;
            T000_READNEXT_DCTBRARC();
            if (pgmRtn) return;
        }
        T000_ENDBR_DCTBRARC();
        if (pgmRtn) return;
    }
    public void B050_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCRBRARC.KEY.BR_NO);
        WS_OUTPUT_VAL.WS_BR_NO = DCRBRARC.KEY.BR_NO;
        CEP.TRC(SCCGWA, DCRBRARC.AREA_NO);
        WS_OUTPUT_VAL.WS_AREA_NO = DCRBRARC.AREA_NO;
        CEP.TRC(SCCGWA, DCRBRARC.AREA_NAME);
        WS_OUTPUT_VAL.WS_AREA_NM = DCRBRARC.AREA_NAME;
        WS_OUTPUT_VAL.WS_CRT_DT = DCRBRARC.CRT_DATE;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_VAL);
        SCCMPAG.DATA_LEN = 159;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        BPCPNHIS.INFO.FMT_ID_LEN = 208;
        if (DCCSBRAR.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.NEW_DAT_PT = DCRBRARC;
        }
        if (DCCSBRAR.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.DATA_FLG = 'Y';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRBRARO;
        }
        if (DCCSBRAR.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = DCRBRARO;
            BPCPNHIS.INFO.NEW_DAT_PT = DCRBRARC;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        WS_OUTPUT_VAL.WS_BR_NO = DCRBRARC.KEY.BR_NO;
        WS_OUTPUT_VAL.WS_AREA_NO = DCRBRARC.AREA_NO;
        WS_OUTPUT_VAL.WS_AREA_NM = DCRBRARC.AREA_NAME;
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = WS_OUTPUT_VAL;
        SCCFMT.DATA_LEN = 159;
        CEP.TRC(SCCGWA, WS_OUTPUT_VAL);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTBRARC() throws IOException,SQLException,Exception {
        DCTBRARC_BR.rp = new DBParm();
        DCTBRARC_BR.rp.TableName = "DCTBRARC";
        DCTBRARC_BR.rp.where = "BR_NO >= :WS_BR_NO_LOW "
            + "AND BR_NO <= :WS_BR_NO_HIGH";
        DCTBRARC_BR.rp.order = "BR_NO";
        IBS.STARTBR(SCCGWA, DCRBRARC, this, DCTBRARC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BRARC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "STARTBR";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTBRARC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRBRARC, this, DCTBRARC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BRARC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READNEXT";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void T000_ENDBR_DCTBRARC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTBRARC_BR);
    }
    public void T000_READ_DCTBRARC() throws IOException,SQLException,Exception {
        DCTBRARC_RD = new DBParm();
        DCTBRARC_RD.TableName = "DCTBRARC";
        DCTBRARC_RD.col = "BR_NO, AREA_NO, AREA_NAME, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRBRARC, DCTBRARC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BRARC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTBRARC() throws IOException,SQLException,Exception {
        DCTBRARC_RD = new DBParm();
        DCTBRARC_RD.TableName = "DCTBRARC";
        DCTBRARC_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRBRARC, DCTBRARC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BRARC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTBRARC_UPD() throws IOException,SQLException,Exception {
        DCTBRARC_RD = new DBParm();
        DCTBRARC_RD.TableName = "DCTBRARC";
        DCTBRARC_RD.upd = true;
        IBS.READ(SCCGWA, DCRBRARC, DCTBRARC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BRARC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTBRARC() throws IOException,SQLException,Exception {
        DCTBRARC_RD = new DBParm();
        DCTBRARC_RD.TableName = "DCTBRARC";
        IBS.REWRITE(SCCGWA, DCRBRARC, DCTBRARC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_TBL_FLAG = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BRARC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_DCTBRARC() throws IOException,SQLException,Exception {
        DCTBRARC_RD = new DBParm();
        DCTBRARC_RD.TableName = "DCTBRARC";
        IBS.DELETE(SCCGWA, DCRBRARC, DCTBRARC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BRARC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DCCSBRAR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
