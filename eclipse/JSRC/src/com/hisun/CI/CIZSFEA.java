package com.hisun.CI;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSFEA {
    DBParm CITFLMT_RD;
    DBParm CITACR_RD;
    DBParm CITFLRL_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CIRFLMT CIRFLMT = new CIRFLMT();
    CIRACR CIRACR = new CIRACR();
    CIRFLRL CIRFLRL = new CIRFLRL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSFEA CICSFEA;
    public void MP(SCCGWA SCCGWA, CICSFEA CICSFEA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSFEA = CICSFEA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSFEA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSFEA.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICSFEA.FUNC == 'A') {
            B020_ADD_FLMT_INF();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FUNC INPUT ERROR");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERR);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSFEA.DATA.SVS_ADC_NO);
        if (CICSFEA.DATA.SVS_ADC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRFLMT);
            CIRFLMT.KEY.SVS_ADC_NO = CICSFEA.DATA.SVS_ADC_NO;
            T000_READ_CITFLMT();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "FLMT INF NOT FND");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FLMT_INF_NOTFND, CICSFEA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, CICSFEA.DATA.AGR_NO);
        if (CICSFEA.DATA.AGR_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICSFEA.DATA.AGR_NO;
            T000_READ_CITACR();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                CEP.TRC(SCCGWA, "ACR INF NOT FND");
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICSFEA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_ADD_FLMT_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRFLRL);
        CIRFLRL.KEY.AGR_NO = CICSFEA.DATA.AGR_NO;
        CIRFLRL.KEY.SVS_ADC_NO = CICSFEA.DATA.SVS_ADC_NO;
        CIRFLRL.REL_STS = '0';
        CIRFLRL.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFLRL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFLRL.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRFLRL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRFLRL.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRFLRL.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITFLRL();
        if (pgmRtn) return;
    }
    public void T000_READ_CITFLMT() throws IOException,SQLException,Exception {
        CITFLMT_RD = new DBParm();
        CITFLMT_RD.TableName = "CITFLMT";
        IBS.READ(SCCGWA, CIRFLMT, CITFLMT_RD);
    }
    public void T000_READ_CITACR() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
    }
    public void T000_WRITE_CITFLRL() throws IOException,SQLException,Exception {
        CITFLRL_RD = new DBParm();
        CITFLRL_RD.TableName = "CITFLRL";
        IBS.WRITE(SCCGWA, CIRFLRL, CITFLRL_RD);
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
