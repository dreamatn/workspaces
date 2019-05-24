package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUDHIS {
    brParm BPTFHIS1_BR = new brParm();
    brParm BPTFHIS2_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    double WS_OD_BAL = 0;
    double WS_DC_BAL = 0;
    char WS_JRN_IN_USE = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRFHIS BPRFHIS = new BPRFHIS();
    BPRFHIS BPRFHIS1 = new BPRFHIS();
    BPRFHIS BPRFHIS2 = new BPRFHIS();
    SCCGWA SCCGWA;
    SCCBATH SCCBATH;
    BPCUDHIS BPCUDHIS;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCRCWAT SCRCWA;
    public void MP(SCCGWA SCCGWA, BPCUDHIS BPCUDHIS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUDHIS = BPCUDHIS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUDHIS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        SCRCWA = (SCRCWAT) GWA_SC_AREA.CWA_AREA_PTR;
        WS_JRN_IN_USE = SCCGWA.COMM_AREA.JRN_IN_USE;
        if (SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            WS_JRN_IN_USE = SCCBATH.JPRM.JRN_IND;
        }
        CEP.TRC(SCCGWA, WS_JRN_IN_USE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "11111");
        CEP.TRC(SCCGWA, BPCUDHIS.INPUT.AC);
        CEP.TRC(SCCGWA, BPCUDHIS.INPUT.LAST_BAL);
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCUDHIS.INPUT.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ACOAC_CANNOT_SPACE, BPCUDHIS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIS);
        BPRFHIS.ACO_AC = BPCUDHIS.INPUT.AC;
        WS_OD_BAL = BPCUDHIS.INPUT.LAST_BAL;
        T000_STARTBR_BPTFHIS();
        if (pgmRtn) return;
        T000_READNEXT_BPTFHIS();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, BPRFHIS1.TX_AMT);
            CEP.TRC(SCCGWA, BPRFHIS2.TX_AMT);
            if (WS_JRN_IN_USE == '1') {
                if (BPRFHIS1.DRCRFLG == 'D' 
                    && BPRFHIS1.TX_STS == 'N') {
                    WS_OD_BAL -= BPRFHIS1.TX_AMT;
                    WS_DC_BAL -= BPRFHIS1.TX_AMT;
                    if (WS_OD_BAL < BPCUDHIS.OUTPUT.OD_BAL) {
                        BPCUDHIS.OUTPUT.OD_BAL = WS_OD_BAL;
                    }
                }
                if (BPRFHIS1.DRCRFLG == 'C' 
                    && BPRFHIS1.TX_STS == 'N') {
                    WS_OD_BAL += BPRFHIS1.TX_AMT;
                    WS_DC_BAL += BPRFHIS1.TX_AMT;
                    if (WS_OD_BAL < BPCUDHIS.OUTPUT.OD_BAL) {
                        BPCUDHIS.OUTPUT.OD_BAL = WS_OD_BAL;
                    }
                }
            } else if (WS_JRN_IN_USE == '2') {
                if (BPRFHIS2.DRCRFLG == 'D' 
                    && BPRFHIS2.TX_STS == 'N') {
                    WS_OD_BAL -= BPRFHIS2.TX_AMT;
                    WS_DC_BAL -= BPRFHIS2.TX_AMT;
                    if (WS_OD_BAL < BPCUDHIS.OUTPUT.OD_BAL) {
                        BPCUDHIS.OUTPUT.OD_BAL = WS_OD_BAL;
                    }
                }
                if (BPRFHIS2.DRCRFLG == 'C' 
                    && BPRFHIS2.TX_STS == 'N') {
                    WS_OD_BAL += BPRFHIS2.TX_AMT;
                    WS_DC_BAL += BPRFHIS2.TX_AMT;
                    if (WS_OD_BAL < BPCUDHIS.OUTPUT.OD_BAL) {
                        BPCUDHIS.OUTPUT.OD_BAL = WS_OD_BAL;
                    }
                }
            }
            BPCUDHIS.OUTPUT.DC_BAL = WS_DC_BAL;
            T000_READNEXT_BPTFHIS();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTFHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_DC_BAL);
        if (BPCUDHIS.OUTPUT.OD_BAL < 0) {
            BPCUDHIS.OUTPUT.OD_BAL = BPCUDHIS.OUTPUT.OD_BAL * -1;
        } else {
            BPCUDHIS.OUTPUT.OD_BAL = 0;
        }
        CEP.TRC(SCCGWA, BPCUDHIS.OUTPUT.OD_BAL);
        CEP.TRC(SCCGWA, BPCUDHIS.OUTPUT.DC_BAL);
    }
    public void T000_STARTBR_BPTFHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_JRN_IN_USE);
        if (WS_JRN_IN_USE == '1') {
            BPTFHIS1_BR.rp = new DBParm();
            BPTFHIS1_BR.rp.TableName = "BPTFHIS1";
            BPTFHIS1_BR.rp.where = "ACO_AC = :BPRFHIS.ACO_AC";
            BPTFHIS1_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            BPTFHIS2_BR.rp = new DBParm();
            BPTFHIS2_BR.rp.TableName = "BPTFHIS2";
            BPTFHIS2_BR.rp.where = "ACO_AC = :BPRFHIS.ACO_AC";
            BPTFHIS2_BR.rp.order = "AC_DT,JRNNO,JRN_SEQ";
            IBS.STARTBR(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        }
    }
    public void T000_READNEXT_BPTFHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_JRN_IN_USE);
        if (WS_JRN_IN_USE == '1') {
            IBS.READNEXT(SCCGWA, BPRFHIS1, this, BPTFHIS1_BR);
        } else {
            IBS.READNEXT(SCCGWA, BPRFHIS2, this, BPTFHIS2_BR);
        }
    }
    public void T000_ENDBR_BPTFHIS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_JRN_IN_USE);
        if (WS_JRN_IN_USE == '1') {
            IBS.ENDBR(SCCGWA, BPTFHIS1_BR);
        } else {
            IBS.ENDBR(SCCGWA, BPTFHIS2_BR);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
