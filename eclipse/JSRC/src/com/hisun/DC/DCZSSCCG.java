package com.hisun.DC;

import com.hisun.BP.*;
import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSSCCG {
    DBParm DCTDCSCC_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_A = "DC941";
    String K_HIS_RMK = "HISTORY FOR DCTDCSCC UPDATED";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String CPN_SCSSCKDT = "SCSSCKDT";
    String CPN_U_BPZPNHIS = "BP-REC-NHIS";
    String WS_MSG_ID = "      ";
    String WS_ERR_INFO = "                                                                                                                        ";
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DCCF941 DCCF941 = new DCCF941();
    DCRDCSCC DCRDCSCC = new DCRDCSCC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCS9401 DCCS9401;
    public void MP(SCCGWA SCCGWA, DCCS9401 DCCS9401) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCS9401 = DCCS9401;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSSCCG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "1111");
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCS9401.SOCIAL_NO);
        CEP.TRC(SCCGWA, DCCS9401.SOCIAL_CARD_NO);
        CEP.TRC(SCCGWA, DCCS9401.CARD_NO);
        CEP.TRC(SCCGWA, DCCS9401.E_CARD_NO);
        CEP.TRC(SCCGWA, DCCS9401.CI_NM);
        CEP.TRC(SCCGWA, DCCS9401.CI_NM_OLD);
        CEP.TRC(SCCGWA, DCCS9401.ID_TYP_OLD);
        CEP.TRC(SCCGWA, DCCS9401.ID_NO_OLD);
        CEP.TRC(SCCGWA, DCCS9401.ID_TYP);
        CEP.TRC(SCCGWA, DCCS9401.ID_NO);
        CEP.TRC(SCCGWA, DCCS9401.SEX);
        CEP.TRC(SCCGWA, DCCS9401.SEX_OLD);
        CEP.TRC(SCCGWA, DCCS9401.CHG_RSN);
        B100_INPUT_CHK_PROC();
        if (pgmRtn) return;
        B200_UPDATE_DCTDCSCC();
        if (pgmRtn) return;
        B300_OUTPUT_LIST();
        if (pgmRtn) return;
        B400_ITE_HISTORY_ADD();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (DCCS9401.SOCIAL_NO.trim().length() == 0 
            || DCCS9401.SOCIAL_CARD_NO.trim().length() == 0 
            || DCCS9401.CARD_NO.trim().length() == 0 
            || DCCS9401.E_CARD_NO.trim().length() == 0 
            || DCCS9401.CI_NM.trim().length() == 0 
            || DCCS9401.CI_NM_OLD.trim().length() == 0 
            || DCCS9401.ID_TYP_OLD.trim().length() == 0 
            || DCCS9401.ID_NO_OLD.trim().length() == 0 
            || DCCS9401.ID_TYP.trim().length() == 0 
            || DCCS9401.ID_NO.trim().length() == 0 
            || DCCS9401.SEX == ' ' 
            || DCCS9401.SEX_OLD == ' ') {
            WS_MSG_ID = DCCMSG_ERROR_MSG.DC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_UPDATE_DCTDCSCC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCSCC);
        DCRDCSCC.KEY.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCSCC.KEY.TXN_JRN = SCCGWA.COMM_AREA.JRN_NO;
        DCRDCSCC.SOCIAL_NO = DCCS9401.SOCIAL_NO;
        DCRDCSCC.SOCIAL_CARD_NO = DCCS9401.SOCIAL_CARD_NO;
        DCRDCSCC.CARD_NO = DCCS9401.CARD_NO;
        DCRDCSCC.E_CARD_NO = DCCS9401.E_CARD_NO;
        DCRDCSCC.CI_NM = DCCS9401.CI_NM;
        DCRDCSCC.CI_NM_OLD = DCCS9401.CI_NM_OLD;
        DCRDCSCC.ID_TYP_OLD = DCCS9401.ID_TYP_OLD;
        DCRDCSCC.ID_NO_OLD = DCCS9401.ID_NO_OLD;
        DCRDCSCC.ID_TYP = DCCS9401.ID_TYP;
        DCRDCSCC.ID_NO = DCCS9401.ID_NO;
        DCRDCSCC.SEX = DCCS9401.SEX;
        DCRDCSCC.SEX_OLD = DCCS9401.SEX_OLD;
        DCRDCSCC.CHG_RSN = DCCS9401.CHG_RSN;
        DCRDCSCC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCSCC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRDCSCC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCSCC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTDCSCC();
        if (pgmRtn) return;
    }
    public void B300_OUTPUT_LIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF941);
        DCCF941.SOCIAL_NO = DCCS9401.SOCIAL_NO;
        DCCF941.CARD_NO = DCCS9401.CARD_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_A;
        SCCFMT.DATA_PTR = DCCF941;
        SCCFMT.DATA_LEN = 49;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, DCCF941.SOCIAL_NO);
        CEP.TRC(SCCGWA, DCCF941.CARD_NO);
    }
    public void B400_ITE_HISTORY_ADD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCRDCSCC";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCS9401.CARD_NO;
        BPCPNHIS.INFO.FMT_ID_LEN = 1083;
        BPCPNHIS.INFO.NEW_DAT_PT = DCRDCSCC;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_WRITE_DCTDCSCC() throws IOException,SQLException,Exception {
        DCTDCSCC_RD = new DBParm();
        DCTDCSCC_RD.TableName = "DCTDCSCC";
        IBS.WRITE(SCCGWA, DCRDCSCC, DCTDCSCC_RD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_BPZPNHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_MSG_ID = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSG_ID);
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
