package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMALH {
    DBParm CITALH_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_RMK = "CI ALH INFO        ";
    String K_HIS_CPY = "CIRALH";
    String K_OUTPUT_FMT_9 = "CI502";
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    char WS_ALH_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRALH CIRALH = new CIRALH();
    CIRALH CIRALHO = new CIRALH();
    CIRALH CIRALHN = new CIRALH();
    CIRBAS CIRBAS = new CIRBAS();
    CICOALHO CICOALHO = new CICOALHO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMALH CICMALH;
    public void MP(SCCGWA SCCGWA, CICMALH CICMALH) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMALH = CICMALH;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMALH return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRALH);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B030_REGISTE_PROC();
        if (pgmRtn) return;
    }
    public void B030_REGISTE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALH);
        IBS.init(SCCGWA, CIRALHO);
        IBS.init(SCCGWA, CIRALHN);
        CEP.TRC(SCCGWA, CICMALH.DATA.ALT_NO);
        CEP.TRC(SCCGWA, CICMALH.DATA.ENTY_TYP);
        CEP.TRC(SCCGWA, CICMALH.DATA.ENTY_NO);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        CEP.TRC(SCCGWA, CICMALH.DATA.STOP);
        CIRALH.KEY.ALT_NO = CICMALH.DATA.ALT_NO;
        CIRALH.KEY.ENTY_TYP = CICMALH.DATA.ENTY_TYP;
        CIRALH.KEY.ENTY_NO = CICMALH.DATA.ENTY_NO;
        CIRALH.KEY.MSG_CHNL = SCCGWA.COMM_AREA.CHNL;
        T000_READ_CITALH();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            B030_01_ADD_PROC();
            if (pgmRtn) return;
        } else {
            B030_02_MODIFY_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_01_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALH);
        IBS.init(SCCGWA, CIRALHO);
        IBS.init(SCCGWA, CIRALHN);
        CIRALH.KEY.ALT_NO = CICMALH.DATA.ALT_NO;
        CIRALH.KEY.ENTY_TYP = CICMALH.DATA.ENTY_TYP;
        CIRALH.KEY.ENTY_NO = CICMALH.DATA.ENTY_NO;
        CIRALH.KEY.MSG_CHNL = SCCGWA.COMM_AREA.CHNL;
        CIRALH.STOP = CICMALH.DATA.STOP;
        CIRALH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRALH.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRALH.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRALH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRALH.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRALH.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITALH();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRALH, CIRALHN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B030_02_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALH);
        IBS.init(SCCGWA, CIRALHO);
        IBS.init(SCCGWA, CIRALHN);
        CIRALH.KEY.ALT_NO = CICMALH.DATA.ALT_NO;
        CIRALH.KEY.ENTY_TYP = CICMALH.DATA.ENTY_TYP;
        CIRALH.KEY.ENTY_NO = CICMALH.DATA.ENTY_NO;
        CIRALH.KEY.MSG_CHNL = SCCGWA.COMM_AREA.CHNL;
        T000_READ_CITALH_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRALH, CIRALHO);
        CIRALH.STOP = CICMALH.DATA.STOP;
        CIRALH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRALH.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRALH.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITALH();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRALH, CIRALHN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void R000_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        CICOALHO.ALT_NO = CIRALH.KEY.ALT_NO;
        CICOALHO.ENTY_TYP = CIRALH.KEY.ENTY_TYP;
        CICOALHO.ENTY_NO = CIRALH.KEY.ENTY_NO;
        CICOALHO.MSG_CHNL = CIRALH.KEY.MSG_CHNL;
        CICOALHO.STOP = CIRALH.STOP;
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = CICOALHO;
        SCCFMT.DATA_LEN = 54;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.CI_NO = CIRALH.KEY.ENTY_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPY;
        BPCPNHIS.INFO.FMT_ID_LEN = 128;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRALHO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRALHN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_READ_CITALH() throws IOException,SQLException,Exception {
        CITALH_RD = new DBParm();
        CITALH_RD.TableName = "CITALH";
        IBS.READ(SCCGWA, CIRALH, CITALH_RD);
    }
    public void T000_WRITE_CITALH() throws IOException,SQLException,Exception {
        CITALH_RD = new DBParm();
        CITALH_RD.TableName = "CITALH";
        IBS.WRITE(SCCGWA, CIRALH, CITALH_RD);
    }
    public void T000_READ_CITALH_UPD() throws IOException,SQLException,Exception {
        CITALH_RD = new DBParm();
        CITALH_RD.TableName = "CITALH";
        CITALH_RD.upd = true;
        IBS.READ(SCCGWA, CIRALH, CITALH_RD);
    }
    public void T000_REWRITE_CITALH() throws IOException,SQLException,Exception {
        CITALH_RD = new DBParm();
        CITALH_RD.TableName = "CITALH";
        IBS.REWRITE(SCCGWA, CIRALH, CITALH_RD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
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
        if (CICMALH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICMALH=");
            CEP.TRC(SCCGWA, CICMALH);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
